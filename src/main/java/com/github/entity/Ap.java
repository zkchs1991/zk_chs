package com.github.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by zk_chs on 16/4/11.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wifi_ap_config")
public class Ap {

    /** 主键id */
    @Id
    @GeneratedValue
    private Integer id;
    /** 此ap或探针属于哪个用户账号 */
    private String username;
    /** 衰减值，非平台业务参数，定位引擎需要此参数进行计算 */
    private Float atten;
    /** 衰减系数，非平台业务参数，定位引擎需要此参数进行计算*/
    private Float pathLoss;
    /** 品牌id，参考ApCoe.java */
    private Integer btId;
    /** 此设备所在楼层号 */
    private Long floorId;
    /** 此设备所在地图id */
    private Long mapId;
    /** 默认楼层高度 */
    private Float height;
    /** 设备的mac，打点的时候输入 */
    private String mac;
    /** 设备的所在的场景id */
    private Long sceneId;
    /** x坐标 */
    private Double x;
    /** y坐标 */
    private Double y;

    /** 设备是否处在使用状态，打过点或者被分配到应用会被置为使用中 */
    @Column(nullable = false)
    private boolean inUse;

    /** 是否是探针 true 探针 false 非探针 */
    @Column(nullable = false)
    private boolean immutable;

    /** 设备使用的定位引擎，定位引擎定义在字典表中 */
    private String locationEngine;

    /** 探针的名称 */
    private String name;
    /** 探针的版本号 */
    private Integer version;
    /** 探针的状态 enable or disable, immutable=true && inUse=true才能获取到status*/
    private String status;
    /** 探针的分组 参考ApGroup*/
    private Integer groupId;
    /** 探针的公网ip，monitor程序init 参考 MonitorProtocolHandler*/
    private String ip;
    /** 探针的最后一次修改时间 参考 MonitorProtocolHandler*/
    private Long lastModify;
    /** 探针的最后一次响应时间 参考 MonitorProtocolHandler*/
    private Long lastResponse;
    /** 探针 wan口的ip，通过云AC心跳获取 */
    private String wanIp;
    /** 探针的最后一次响应时间 参考 MonitorProtocolHandler*/
    private Long firstResponse;

}
