package com.ywx.common.net.model

/**
 * Author: YWX
 * Date: 2021/12/16 10:55
 * Description: 在线表盘
 */
class OnlineDialFace {
    lateinit var crc: String
    lateinit var binProtocol: String
    lateinit var dialShape: String
    lateinit var fileUrl: String
    lateinit var previewUrl: String

    /**
     * 下载进度
     */
    var process: Int = 0

    /**
     * 是否选中
     */
    var isChecked: Boolean = false

    override fun toString(): String {
        return "OnlineDialFace(crc='$crc', binProtocol='$binProtocol', dialShape='$dialShape', fileUrl='$fileUrl', previewUrl='$previewUrl', process=$process, isChecked=$isChecked)"
    }

}