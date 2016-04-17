-- 该脚本用来实现访问频率的限制
local times = redis.call('incr', KEYS[1])

if times == 1 then
    --此时表示KEYS[1]为刚刚创建,所以为其设置生存时间
    redis.call('expire', KEYS[1], ARGV[1])
end

if times > tonumber(ARGV[2]) then
    return 0
end

return 1