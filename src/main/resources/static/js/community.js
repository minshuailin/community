/**
 * 提交回复
 * */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();

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
            "parentId" :questionId,
            "content" :content,
            "type" : 1
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
 * 展开二级评论
 * */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var commentsId = $("#comment-"+id);
    var attribute = e.getAttribute("data-collapse");
    if(attribute){
        commentsId.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else {
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