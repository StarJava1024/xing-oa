import request from '@/utils/request'

const api_name = '/admin/system/sysUser'

export default {

  getPageList(current, limit, searchObj) {
    // return request({
    //   url: `${api_name}/${current}/${limit}`,
    //   method: 'get',
    //   params: searchObj // url查询字符串或表单键值对
    // })
    return request({
      url: `${api_name}`,
      method: 'get',
      params: {
        keyword: searchObj.keyword,
        createTimeBegin: searchObj.createTimeBegin,
        createTimeEnd: searchObj.createTimeEnd,
        current: current,
        limit: limit
      }
    })
  },
  getById(id) {
    return request({
      url: `${api_name}/get/${id}`,
      method: 'get'
    })
  },

  save(role) {
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: role
    })
  },

  updateById(role) {
    return request({
      url: `${api_name}/update`,
      method: 'put',
      data: role
    })
  },
  removeById(id) {
    return request({
      url: `${api_name}/remove/${id}`,
      method: 'delete'
    })
  },
  updateStatus(id, status) {
    return request({
      url: `${api_name}/updateStatus/${id}/${status}`,
      method: 'get'
    })
  }
}
