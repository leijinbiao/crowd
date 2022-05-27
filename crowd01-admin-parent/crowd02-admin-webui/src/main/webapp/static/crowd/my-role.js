function fillAuthTree() {
    let ajaxResult = $.ajax({
        "url": "assign/auth",
        "type": "post",
        "dataType": "json",
        "async": false
    });

    let statusCode = ajaxResult.status;

    let resultEntity = ajaxResult.responseJSON;

    let result = resultEntity.result;

    if (result == "FAILED") {
        layer.msg(resultEntity.message)
        return;
    }

    if (statusCode != 200) {
        layer.msg("失败！响应状态码=" + statusCode + " 说明信息=" + ajaxResult.statusText)
        return;
    }

    let setting = {
        "data": {
            "simpleData": {
                "enable": true,
                "pIdKey": "categoryId"
            },
            "key": {
                "name": "title"
            }
        },
        "check": {
            "enable": true
        }
    };

    let authList = resultEntity.data;

    $.fn.zTree.init($("#authTreeDemo"), setting, authList);

    let zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");

    zTreeObj.expandAll(true)

    ajaxResult = $.ajax({
        "url": "assign/auth/id/by/role/id",
        "type": "post",
        "data": {
            "roleId": window.roleId
        },
        "dataType": "json",
        "async": false
    });

    if (statusCode != 200) {
        layer.msg("失败！响应状态码=" + statusCode + " 说明信息=" + ajaxResult.statusText)
        return;
    }

    let authIdArray = ajaxResult.responseJSON.data;

    for (let i = 0; i < authIdArray.length; i++) {
        let authId = authIdArray[i];

        let treeNode = zTreeObj.getNodeByParam("id", authId);

        // 表示节点选中
        let checked = true;
        // 是否联动
        let checkTypeFlag = false;

        zTreeObj.checkNode(treeNode, checked, checkTypeFlag);
    }


}

/**
 * 生成 roleid 的数组
 * @param roleArray
 */
function generateRoleId(roleArray) {
    window.roleIdArray = [];
    window.deleteItemName = "";
    // 遍历roleArray数组
    for (var i = 0; i < roleArray.length; i++) {
        var role = roleArray[i];
        var roleName = role.roleName;
        window.deleteItemName += "<br>[  " + roleName + "  ]";
        var roleId = role.roleId;
        // 调用数组对象的push()方法存入新元素
        window.roleIdArray.push(roleId);
    }

}

/**
 * 渲染页面的内容
 */
function generate() {
    let pageInfo = getPageInfoRemote();

    if(pageInfo == undefined) return ;

    fillTableBody(pageInfo)

    generateNavigator(pageInfo)
}

/**
 * 请求获取分页数据
 * @returns {*}
 */
function getPageInfoRemote() {
    let ajaxResult = $.ajax({
        "url": "role/pageInfo",
        "type": "post",
        "data": {
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyword": window.keyword
        },
        "dataType": "json",
        "async": false,
    });

    let statusCode = ajaxResult.status;

    let resultEntity = ajaxResult.responseJSON;

    console.log(ajaxResult.responseJSON);

    let result = resultEntity.result;

    if (result == "FAILED") {
        layer.msg(resultEntity.message)
        return;
    }

    if (statusCode != 200) {
        layer.msg("失败！响应状态码=" + statusCode + " 说明信息=" + ajaxResult.statusText)
        return;
    }

    let pageInfo = resultEntity.data;

    return pageInfo;
}

/**
 * 填充表单
 * @param pageInfo
 */
function fillTableBody(pageInfo) {
    // 清除tbody中的旧的内容
    $("#rolePageBody").empty();

    // 这里清空是为了让没有搜索结果时不显示页码导航条
    $("#Pagination").empty();

    // 判断pageInfo对象是否有效
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0) {
        $("#rolePageBody").append("<tr><td colspan='4' align='center'>抱歉！没有查询到您搜索的数据！</td></tr>");
        return;
    }

    // 使用pageInfo的list属性填充tbody
    for (let i = 0; i < pageInfo.list.length; i++) {

        let role = pageInfo.list[i];

        let roleId = role.id;

        let roleName = role.name;

        let numberTd = "<td>" + (i + 1) + "</td>";
        let checkboxTd = "<td><input id='" + roleId + "' class='itemBox' type='checkbox'></td>";
        let roleNameTd = "<td>" + roleName + "</td>";

        let checkBtn = "<button id='" + roleId + "' type='button' class='btn btn-success btn-xs checkBtn'><i class=' glyphicon glyphicon-check'></i></button>";

        let pencilBtn = "<button id='" + roleId + "' type='button' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";

        let removeBtn = "<button id='" + roleId + "' type='button' class='btn btn-danger btn-xs removeBtn'><i class=' glyphicon glyphicon-remove'></i></button>";

        let buttonTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";

        let tr = "<tr>" + numberTd + checkboxTd + roleNameTd + buttonTd + "</tr>";

        $("#rolePageBody").append(tr);
    }

    generateNavigator(pageInfo)
}

function generateNavigator(pageInfo) {

    $("#Pagination").pagination(pageInfo.total, {
        num_edge_entries: 3, //边缘页数
        num_display_entries: 4, //主体页数
        current_page: pageInfo.pageNum - 1,
        callback: pageselectCallback,
        items_per_page: pageInfo.pageSize, //每页显示1项
        prev_text: "上一页",
        next_text: "下一页"
    });

}

function pageselectCallback(page_index, jq) {
    // 修改window对象的pageNum属性
    window.pageNum = page_index + 1;

    // 调用分页函数
    generate();

    // 取消页码超链接的默认行为
    return false;
}