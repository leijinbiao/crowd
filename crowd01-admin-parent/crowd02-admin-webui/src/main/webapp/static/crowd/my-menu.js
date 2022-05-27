function generateTree(){
    $.ajax({
        "url":"menu/pageInfo",
        "type":"post",
        "dataType":"json",
        "success":function(res){
            if(res.result=="SUCCESS"){
                var setting = {
                    view:{
                        "addDiyDom":myAddDiyDom,
                        "addHoverDom":myHoverDom,
                        "removeHoverDom":myRemoveHoverDom
                    },
                    data:{
                        key:{
                            url:"bobo"
                        }
                    }
                };
                var zNodes = res.data;

                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }
            if(res.result=="FAILED"){
                layer.msg(res.message)
            }
        },
        "error":function(res){
            layer.msg(res.status+" "+res.statusText);
        }
    })
}

function myAddDiyDom(treeId,treeNode){
    //通过 treeNode 拼接 span 的 id
    let spanId = treeNode.tId+"_ico";

    $("#"+spanId).removeClass().addClass(treeNode.icon)
}

function myHoverDom(treeId,treeNode){
    let btnGrpId = treeNode.tId+"_btnGrp";
    let anchor = treeNode.tId+"_a";

    if($("#"+btnGrpId).length > 0) return;

    let addBtn = "<a id='"+treeNode.id+"' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    let removeBtn = "<a id='"+treeNode.id+"' class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='删除节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    let editBtn = "<a id='"+treeNode.id+"' class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='修改节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";

    // 获取当前节点的级别数据
    let level = treeNode.level;
    let btnHtml = "";
    if(level == 0){
        // 级别为0时是根节点，只能添加子节点
        btnHtml = addBtn;
    }

    if(level == 1){
        // 级别为1时是分支节点，可以添加子节点、修改
        btnHtml = addBtn + " " + editBtn;
        // 查看当前节点是否有子节点
        let len = treeNode.children.length;
        // 如果没有子节点，就插入 removeBtn 按钮
        if(len==0){
            btnHtml+=" " + removeBtn;
        }
    }

    if(level == 2){
        btnHtml = editBtn + " " + removeBtn;
     }

    $("#"+anchor).after("<span id='"+btnGrpId+"'>"+btnHtml+"</span>")


}

function myRemoveHoverDom(treeId,treeNode){
    let btnGrpId = treeNode.tId+"_btnGrp";
    $("#"+btnGrpId).remove()
}
