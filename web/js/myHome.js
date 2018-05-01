window.onload = function(){
    var divpx1 = document.getElementById("rightManageQ");
    var divpx2 = document.getElementById("rightMy");
    var divpx3 = document.getElementById("rightManageT");
    divpx2.style.display = "none";
    divpx3.style.display = "none";
    document.getElementById("manageQuestion").onclick = function(){
        divpx1.style.display = "block"; //加上这句在显示第二个div的同时，隐藏第一个div。不加这句两个div都显示。
        divpx2.style.display = "none";
        divpx3.style.display = "none";
    };
    document.getElementById("myQuestion").onclick = function(){
        divpx1.style.display = "none"; //加上这句在显示第二个div的同时，隐藏第一个div。不加这句两个div都显示。
        divpx2.style.display = "block";
        divpx3.style.display = "none";
    };
    document.getElementById("manageTeam").onclick = function(){
        divpx1.style.display = "none"; //加上这句在显示第二个div的同时，隐藏第一个div。不加这句两个div都显示。
        divpx2.style.display = "none";
        divpx3.style.display = "block";
    };
};