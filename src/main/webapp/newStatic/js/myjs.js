var curIndex = 0;
var nextIndex = 0;

function ControlBanner() {
    var bannerDiv = document.getElementsByClassName("hero__content hero-content-style1");
    if (bannerDiv.length > 1) {
        nextIndex = Number(curIndex) + Number(1);

        bannerDiv[curIndex].style.display = "none";

        nextIndex = nextIndex == bannerDiv.length ? 0 : nextIndex;
        bannerDiv[nextIndex].style.display = "block";

        curIndex = nextIndex;
    }
}
setInterval("ControlBanner()", 8300);//8.3秒 


jQuery(function () {
    jQuery(".mySolutionCarousel").carousel({ interval: 10000 });//每隔5秒自动轮播
    // 循环轮播到上一个项目
    jQuery(".prev-slide").click(function (e) {
        var myCarousel = jQuery(e.target).parents('.carousel');
        myCarousel.carousel('prev');
    });
    // 循环轮播到下一个项目
    jQuery(".next-slide").click(function (e) {
        var myCarousel = jQuery(e.target).parents('.carousel');
        myCarousel.carousel('next');
    });
    // 跳到指定轮播项目
    jQuery(".slide-li-item").click(function (e) {
        var i = parseInt(e.target.getAttribute('data-slide-to')),
            myCarousel = jQuery(e.target).parents('.carousel');
        myCarousel.carousel(i);
    });
});
