function buttonDisplay(){
    function btFadein(){
        $("#enterBt").fadeIn(1000);
    }
    setTimeout(btFadein,2000);
}
window.onload=buttonDisplay;

function wordPlay(){
    $("#enterBt").remove();
    $("#line").fadeIn(500);
    $("#welcome").fadeIn(2000);
    function jump() {
        // window.location.href = "../templates/account/signon.html";
        //上面这个要切记目录的问题，因为此时的index.html是在static文件夹下，而目标页面是在另外一个文件夹下
        // 所以要先返回上一级，再指定template目录下
        //不过在这个地方，由于用到了controller，所以就不再使用上面那个操作，不过以后在其他地方还可能会用到，可以记住
        window.location.href = "/account/enter";//这个是与controller有关，在其中实现页面跳转
    }
    setTimeout(jump,3000);
}
