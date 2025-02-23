import request from '@/utils/request'

// 查询帖子列表
export function listPost(query) {
  return request({
    url: '/bbs/post/list',
    method: 'get',
    params: query
  })
}

// 查询帖子详细
export function getPost(postId) {
  return request({
    url: '/bbs/post/' + postId,
    method: 'get'
  })
}

// 新增帖子
export function addPost(data) {
  return request({
    url: '/bbs/post',
    method: 'post',
    data: data
  })
}

// 修改帖子
export function updatePost(data) {
  return request({
    url: '/bbs/post',
    method: 'put',
    data: data
  })
}

// 删除帖子
export function delPost(postId) {
  return request({
    url: '/bbs/post/' + postId,
    method: 'delete'
  })
}
