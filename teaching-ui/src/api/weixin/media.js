import request from '@/utils/request'

// 查询微信媒体列表
export function listMedia(query) {
  return request({
    url: '/weixin/media/list',
    method: 'get',
    params: query
  })
}

// 查询微信媒体详细
export function getMedia(mediaId) {
  return request({
    url: '/weixin/media/' + mediaId,
    method: 'get'
  })
}

// 新增微信媒体
export function addMedia(data) {
  return request({
    url: '/weixin/media',
    method: 'post',
    data: data
  })
}

// 修改微信媒体
export function updateMedia(data) {
  return request({
    url: '/weixin/media',
    method: 'put',
    data: data
  })
}

// 删除微信媒体
export function delMedia(mediaId) {
  return request({
    url: '/weixin/media/' + mediaId,
    method: 'delete'
  })
}
