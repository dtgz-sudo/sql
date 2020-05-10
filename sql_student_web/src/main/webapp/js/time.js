var stime = "1587958144";
function updatetime(){
    stime ++;
    var etime = new Date(parseInt(stime)*1000);
    $("#dt").html(etime.toString("Y-m-d H:i:s"));
}
setInterval(updatetime,1000);
window.dataLayer = window.dataLayer || [];
function gtag(){dataLayer.push(arguments);}
gtag('js', new Date());
gtag('config', 'UA-107784973-2');