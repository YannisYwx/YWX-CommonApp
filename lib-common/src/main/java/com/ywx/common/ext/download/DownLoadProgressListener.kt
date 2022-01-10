package com.ywx.common.ext.download

/**
 * Author: YWX
 * Date: 2021/9/15 18:03
 * Description:
 */

interface DownLoadProgressListener {

    /**
     * 下载进度
     * @param key url
     * @param progress  进度
     * @param read  读取
     * @param count 总共长度
     * @param done  是否完成
     */
    fun onUpdate(key: String, progress: Int, read: Long, count: Long, done: Boolean)
}


interface OnDownLoadListener : DownLoadProgressListener {

    /**
     * 等待下载
     *
     * @param key
     */
    fun onDownLoadPrepare(key: String)

    /**
     * 下载失败
     *
     * @param key
     * @param throwable
     */
    fun onDownLoadError(key: String, throwable: Throwable)

    /**
     * 下载成功
     *
     * @param key  key
     * @param path 路径
     * @param size 文件大小
     */
    fun onDownLoadSuccess(key: String, path: String, size: Long)

    /**
     * 下载暂停
     *
     * @param key
     */
    fun onDownLoadPause(key: String)
}