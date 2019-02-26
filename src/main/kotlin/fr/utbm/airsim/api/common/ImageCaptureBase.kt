package fr.utbm.airsim.api.common

import org.msgpack.annotation.MessagePackOrdinalEnum

@MessagePackOrdinalEnum
enum class ImageType(val value: Int) {
    SCENE(0),
    DEPTH_PLANNER(1),
    DEPTH_PERSPECTIVE(2),
    DEPTH_VIS(3),
    DISPARITY_NORMALIZED(4),
    SEGMENTATION(5),
    SURFACE_NORMALS(6),
    INFRARED(7),
    COUNT(8)
}