$(function(){var e=1;$(window).bottom(),$(window).bind("bottom",function(){var t=$(this);t.data("loading")||(t.data("loading",!0),!document.querySelector(".loader")&&$(".loading").append('<div class="loader"></div>'),fetch("post/loadtl.do?page="+ ++e +"&" + location.search.substring(1)).then(function(t){return t.text()}).then(function(t){if(!t.trim())throw"empty";document.querySelector(".timeline").insertAdjacentHTML("beforeend",t),loadTL()}).catch(function(t){e--}).finally(function(){document.querySelector(".loader").remove(),t.data("loading",!1)}))})});
