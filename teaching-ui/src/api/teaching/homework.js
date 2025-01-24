import request from '@/utils/request'

// 查询作业布置列表
export function listHomework(query) {
  return request({
    url: '/teaching/homework/list',
    method: 'get',
    params: query
  })
}

// 查询作业布置详细
export function getHomework(homeworkId) {
  return request({
    url: '/teaching/homework/' + homeworkId,
    method: 'get'
  })
}

// 新增作业布置
export function addHomework(data) {
  return request({
    url: '/teaching/homework',
    method: 'post',
    data: data
  })
}

// 修改作业布置
export function updateHomework(data) {
  return request({
    url: '/teaching/homework',
    method: 'put',
    data: data
  })
}

// 删除作业布置
export function delHomework(homeworkId) {
  return request({
    url: '/teaching/homework/' + homeworkId,
    method: 'delete'
  })
}
