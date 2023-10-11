import request from '@/utils/request'

const api_name = '/admin/system/sysRole'

export default {
  //角色列表-条件分页查询
  getPageList(current, limit, searchObj) {
    return request({
      // url: `${api_name}/${current}/${limit}`,
      method: 'get',
      //如果普通对象参数写法 params:对象参数名称
      //如果使用json格式传递，data:对象参数名称
      // params: searchObj
      url: `${api_name}`,
      params: {
        roleName: searchObj.roleName,
        current: current,
        limit: limit
      }
    })
  },
  //角色删除
  removeById(id) {
    return request({
      url: `${api_name}/${id}`,
      method: 'delete'
    })
  },
  //角色添加
  saveRole(role) {
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: role
    })
  },
  //根据id查询
  getById(id) {
    return request({
      url: `${api_name}/${id}`,
      method: 'get'
    })
  },
  //修改
  updateById(role) {
    return request({
      url: `${api_name}/update`,
      method: 'put',
      data: role
    })
  },
  //批量删除
  batchRemove(ids) {
    return request({
      url: `${api_name}/batchRemove`,
      method: 'delete',
      data: ids
    })
  },
  getRoles(adminId) {
    return request({
      url: `${api_name}/toAssign/${adminId}`,
      method: 'get'
    })
  },

  assignRoles(assginRoleVo) {
    return request({
      url: `${api_name}/doAssign`,
      method: 'post',
      data: assginRoleVo
    })
  }
}
