// $(window).ready(function (){
//     $("#btn_center").click(function () {
//         $('.mask').css('display', 'block');
//         $('.mask').css('width', $(window).width())
//         $('.mask').css('height', $(document).height())
//         popCenterWindow();
//     });
// });
// //获取窗口的高度
// var windowHeight;
// //获取窗口的宽度
// var windowWidth;
// //获取弹窗的宽度
// var popWidth;
// //获取弹窗高度
// var popHeight;
//
// function init() {
//     windowHeight = $(window).height();
//     windowWidth = $(window).width();
//     popHeight = $(".window").height();
//     popWidth = $(".window").width();
// }
// //关闭窗口的方法
// function closeWindow() {
//     $(".close_btn").click(function () {
//         $('.window').hide("slow");
//         $('.mask').css('display', 'none');
//     });
// }
// //定义弹出居中窗口的方法
// function popCenterWindow() {
//     init();
//     //计算弹出窗口的左上角X的偏移量
//     var popX = (windowWidth - popWidth) / 2;
//     // 计算弹出窗口的左上角Y的偏移量为窗口的高度 - 弹窗高度 / 2 + 被卷去的页面的top
//     var popY = (windowHeight - popHeight) / 2 + $(document).scrollTop();
//     //设定窗口的位置
//     $("#center").css("top", popY).css("left", popX).slideToggle("fast");
//     closeWindow();
// }

var modal = (function (){//declare modal object
    var $window = $(window);
    var $modal = $('<div class="modal"/>');//Create markup for modal
    var $content = $('<div class="modal-content"/>');
    var $close = $('<button role="button" class="modal-close">close</button>');

    $modal.append($content,$close);//add close button to modal

    $close.on('click',function (e){//if user click on close
        e.preventDefault();//prevent link behavior
        modal.close();//close the modal window
    });
    return{//add code to modal
        center: function (){//define center() method
            //calculate distance from top and left of window to center the modal
            var top = Math.max($window.height() - $modal.outerHeight(),0)/2;
            var left = Math.max($window.width() - $modal.outerWidth(),0)/2;
            $modal.css({//set css for the modal
                top:top + $window.scrollTop(),//center vertically
                left:left + $window.scrollLeft()//center horizontally
            });
        },
        open:function (settings){//define open method
            $content.empty().append(settings.content);
            //set new content of modal
            $modal.css({//set modal dimensions
                width:settings.width || 'auto',//set width
                height:settings.height || 'auto'//set height
            }).appendTo('body');//add it to the page
            modal.center();//call center() method
            $(window).on('resize',modal.center);
            //call it if window resized
        },
        close:function (){//define close() method
            $content.empty();//remove content from modal
            $modal.detach();//remove modal from page
            $(window).off('resize',modal.center);//remove event handler
        }
    };
}());