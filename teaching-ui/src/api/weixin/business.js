import request from '@/utils/request'

// 查询服务列表
export function listBusiness(query) {
  return request({
    url: '/weixin/business/list',
    method: 'get',
    params: query
  })
}

// 查询服务详细
export function getBusiness(businessId) {
  return request({
    url: '/weixin/business/' + businessId,
    method: 'get'
  })
}

// 新增服务
export function addBusiness(data) {
  return request({
    url: '/weixin/business',
    method: 'post',
    data: data
  })
}

// 修改服务
export function updateBusiness(data) {
  return request({
    url: '/weixin/business',
    method: 'put',
    data: data
  })
}

// 删除服务
export function delBusiness(businessId) {
  return request({
    url: '/weixin/business/' + businessId,
    method: 'delete'
  })
}
