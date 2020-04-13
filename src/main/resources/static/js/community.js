function commentTarget(targetId, type, content) {
    if(!content){
        alert("不能回复空内容");
        return;
    }
    /*    console.log(questionId);
        console.log(content);*/

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parentId" :targetId,
            "content" :content,
            "type" : type
        }),
        success: function (response){
            if(response.code == 200){
                window.location.reload();
            }else {
                if(response.code == 2003){
                    var isAccept = confirm(response.message);
                    if(isAccept){
                        window.open("https://github.com/login/oauth/authorize?client_id=f786a53908a42504fb30&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }else {
                    alert(response.message);
                }

            }
            console.log(response);
        },
        dataType:"json"
    })
}
/**
 * 提交回复
 * */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();

    commentTarget(questionId,1,content);
}
/** 
 * 提交评论中的评论
 * */

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-"+ commentId).val();
    commentTarget(commentId,2,content);
}
/**
 * 展开二级评论
 * */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var commentsId = $("#comment-"+id);

    //获取二级评论的展开状态
    var attribute = e.getAttribute("data-collapse");
    if(attribute){
        //折叠二级评论
        commentsId.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else {
        $.getJSON("/comment/"+id,function (data) {
            console.log(data)
        })
        //展开二级评论
        commentsId.addClass("in");
        e.setAttribute("data-collapse","in");
        e.classList.add("active");
    }
   /* commentsId.toggleClass("in");
    if(commentsId.hasClass("collapse in")){
        $(".comment-icon").addClass("active");
    }else {
        $(".comment-icon").removeClass("active");
    }*/
    console.log(id);
}