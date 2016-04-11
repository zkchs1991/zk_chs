package com.github.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.*;

@Slf4j
public class M {

    // ==========================================builder===============================================

    public static M.MBuilder builder() {
        return new M.MBuilder();
    }

    public static class MBuilder {

        private Map<String, Object> map;

        MBuilder() {
            map = Maps.newTreeMap();
        }

        public M.MBuilder put(String k, Object v) {
            map.put(k, v);
            return this;
        }

        public M.MBuilder put(String k, Object v , Object defaultValue) {
            if (null == v) {
                map.put(k, defaultValue);
            } else {
                map.put(k, v);
            }
            return this;
        }

        public M.MBuilder putIf(boolean flag, String k, Object v) {
            return flag ? put(k, v) : this;
        }

        public M.MBuilder putIf(boolean flag, String k, Object v, Object defaultValue) {
            return flag ? put(k, v ,defaultValue) : this;
        }

        public M.MBuilder computeIf(boolean flag, String k, Supplier<Object> mappingFunction) {
            return flag ? put(k, mappingFunction.get()) : this;
        }

        public M.MBuilder computeIf(boolean flag, String k, Function<String, Object> mappingFunction, Object defaultValue) {
            return flag ? put(k, mappingFunction.apply(k), defaultValue) : this;
        }

        public Map<String, Object> buildMap() {
            return map;
        }

    }


    // ==========================================tools===============================================

    public static <V, T> T get(Map<String, V> map, String key, Class<T> clazz) {
        V value = map.get(key);
        return null == value ? null : (T) value;
    }

    public static <V, T> T get(Map<String, V> map, String key, T defaultValue, Class<T> clazz) {
        V value = map.get(key);
        return null == value ? defaultValue : (T) value;
    }

    public static <V, E extends Enum<E>> E getEnum(Map<String, V> map, String key, Class<E> clazz) {
        String value = get(map, key, String.class);
        try {
            return null == value ? null : Enum.valueOf(clazz, value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static String getString(Map<String, ?> map, String key) {
        return get(map, key, String.class);
    }

    public static String getStringTrimToNull(Map<String, ?> map, String key) {
        return StringUtils.trimToNull(get(map, key, String.class));
    }

    public static String getString(Map<String, ?> map, String key, String defaltValue) {
        return get(map, key, defaltValue, String.class);
    }

    public static Long getLong(Map<String, ?> map, String key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Long) {
            return (Long) answer;
        }
        return answer.longValue();
    }

    public static long getLongValue(Map<String, ?> map, String key) {
        Long value = getLong(map, key);
        return null == value ? 0L : value;
    }

    public static Integer getInteger(Map<String, ?> map, String key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Integer) {
            return (Integer) answer;
        }
        return answer.intValue();
    }

    public static int getIntegerValue(Map<String, ?> map, String key) {
        Integer value = getInteger(map, key);
        return null == value ? 0 : value;
    }

    public static Double getDouble(Map<String, ?> map, String key) {
        return get(map, key, Double.class);
    }

    public static double getDoubleValue(Map<String, ?> map, String key) {
        Double value = getDouble(map, key);;
        return null == value ? 0.0 : value;
    }

    public static Float getFloat(Map<String, ?> map, String key) {
        return get(map, key, Float.class);
    }

    public static double getFloatValue(Map<String, ?> map, String key) {
        Float value = getFloat(map, key);
        return null == value ? 0.0 : value;
    }

    public static Boolean getBoolean(Map<String, ?> map, String key) {
        return get(map, key, Boolean.class);
    }

    public static boolean getBooleanValue(Map<String, ?> map, String key) {
        Boolean value = getBoolean(map, key);
        return null == value ? false : value;
    }

    public static List<String> getStringList(Map<String, ?> map, String key) {
        Object object = map.get(key);
        try {
            if (object == null) {
                return Lists.newArrayList();
            }
            return (List<String>) object;
        } catch (Exception e) {
            log.error("could not cast {} to string array", object);
            return Lists.newArrayList();
        }

    }

    public static List<Long> getLongList(Map<String, ?> map, String key) {
        try {
            Object object = map.get(key);
            if (object == null) {
                return Lists.newArrayList();
            }
            List<Integer> list =  (List<Integer>) object;
            return list.stream().map(Integer::longValue).collect(toList());
        } catch (Exception e) {
            log.error("could not cast {} to long list");
            return Lists.newArrayList();
        }
    }

    public static List<Integer> getIntegerList(Map<String, ?> map, String key) {
        try {
            Object object = map.get(key);
            if (object == null) {
                return Lists.newArrayList();
            }
            List<Integer> list =  (List<Integer>) object;
            return list;
        } catch (Exception e) {
            log.error("could not cast {} to long list");
            return Lists.newArrayList();
        }
    }

    public static Number getNumber(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                if (answer instanceof Number) {
                    return (Number) answer;

                } else if (answer instanceof String) {
                    try {
                        String text = (String) answer;
                        return NumberFormat.getInstance().parse(text);

                    } catch (ParseException e) {
                        log.info("ParseException", e);
                    }
                }
            }
        }
        return null;
    }

}
