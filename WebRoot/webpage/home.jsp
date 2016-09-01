<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/webpage/context/top.jsp"%>

<script src="<%=basePath%>plug-in/jquery/js/jquery-3.1.0.slim.min.js"></script>
<script type="text/javascript">
function getImg()
{
/*$(document).ready(function() {*/
	$.ajax({
        url:'<%=basePath%>home/getImg',
        type:'POST',
        data:{},
        dataType:'json',
        async:false,
        success:function(data) {
            if(data) {
            	$("#img1").attr("src", data.image[0].image); 
            	if(data.image[0].tag == '1')
            		$("#div1").html("<h1>"+ data.image[0].title + "</h1><p class=\"text-left\">"+ data.image[0].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>surveys/"+ data.image[0].tagId + "\" role=\"button\">点击进入调查</a></p>"); 
            		//$("#div1").innerHTML="<h1>"+ data.image[0].title + "</h1><p class=\"text-left\">"+ data.image[0].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>surveys/"+ data.image[0].tagId + "\" role=\"button\">点击进入调查</a></p>";
            	else
            		$("#div1").html("<h1>"+ data.image[0].title + "</h1><p class=\"text-left\">"+ data.image[0].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>reports/"+ data.image[0].tagId + "\" role=\"button\">点击查看报告</a></p>");
            		//$("#div1").innerHTML="<h1>"+ data.image[0].title + "</h1><p class=\"text-left\">"+ data.image[0].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>reports/"+ data.image[0].tagId + "\" role=\"button\">点击查看报告</a></p>";
            		
            	$("#img2").attr("src", data.image[1].image);
            	if(data.image[1].tag == '1')
            		$("#div2").html("<h1>"+ data.image[1].title + "</h1><p class=\"text-left\">"+ data.image[1].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>surveys/"+ data.image[1].tagId + "\" role=\"button\">点击进入调查</a></p>");
            		//$("#div2").innerHTML="<h1>"+ data.image[1].title + "</h1><p class=\"text-left\">"+ data.image[1].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>surveys/"+ data.image[1].tagId + "\" role=\"button\">点击进入调查</a></p>";
            	else
            		$("#div2").html("<h1>"+ data.image[1].title + "</h1><p class=\"text-left\">"+ data.image[1].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>reports/"+ data.image[1].tagId + "\" role=\"button\">点击查看报告</a></p>");
            		//$("#div2").innerHTML="<h1>"+ data.image[1].title + "</h1><p class=\"text-left\">"+ data.image[1].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>reports/"+ data.image[1].tagId + "\" role=\"button\">点击查看报告</a></p>";
            	
            	$("#img3").attr("src", data.image[2].image);
            	if(data.image[2].tag == '1')
            		$("#div3").html("<h1>"+ data.image[2].title + "</h1><p class=\"text-left\">"+ data.image[2].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>surveys/"+ data.image[2].tagId + "\" role=\"button\">点击进入调查</a></p>");
            		//$("#div3").innerHTML="<h1>"+ data.image[2].title + "</h1><p class=\"text-left\">"+ data.image[2].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>surveys/"+ data.image[2].tagId + "\" role=\"button\">点击进入调查</a></p>";
            	else
            		$("#div3").html("<h1>"+ data.image[2].title + "</h1><p class=\"text-left\">"+ data.image[2].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>reports/"+ data.image[2].tagId + "\" role=\"button\">点击查看报告</a></p>");
            		//$("#div3").innerHTML="<h1>"+ data.image[2].title + "</h1><p class=\"text-left\">"+ data.image[2].description + "</p><p><a class=\"btn btn-lg btn-primary\" href=\"<%=basePath%>reports/"+ data.image[2].tagId + "\" role=\"button\">点击查看报告</a></p>";
            }
        },
    });
/*});*/
}

window.onload=getImg;
</script>
    <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox"><!--   -->
        <div class="item active">
          <img id="img1" class="first-slide" src="plug-in/img/default.jpg"  alt="First slide">
          <div class="container">
            <div id="div1" class="carousel-caption" >
            <!-- 
	              <h1>顾客满意度专项调查</h1>
	              <p class="text-left">
	                将顾客的主观感受量化成定量的指数，客观评价顾客对产品、服务的满意程度，为企业持续改进提供支持。
	              </p>
	              <p><a class="btn btn-lg btn-primary" href="#" role="button">点击进入调查</a></p>
	          -->
            </div>
          </div>
        </div>
        <div class="item">
          <img id="img2" class="second-slide" src="plug-in/img/default1.jpg" alt="Second slide">
          <div class="container">
            <div id="div2" class="carousel-caption">
            <!-- 
	              <h1>品牌知名度专项调查</h1>
	              <p class="text-left">
	                将顾客对品牌主观感受量化成定量的指数，客观评价顾客对品牌的认可程度，为企业品牌建设提供支撑。
	              </p>
	              <p><a class="btn btn-lg btn-primary" href="#" role="button">点击进入调查</a></p>
	           -->
            </div>
          </div>
        </div>
        <div class="item">
          <img id="img3" class="third-slide" src="plug-in/img/default2.jpg" alt="Third slide">
          <div class="container">
            <div id="div3" class="carousel-caption">
            	<!-- 
	              <h1>质量竞争力指数报告</h1>
	              <p class="text-left">
	                区域质量竞争力指数报告发布，采用统一的评价标准对区域宏观质量竞争力水平进行评价，并以指数形式发布，通过报告可以了解区域质量发展的相对优势及发展中存在的问题。
	              </p>
	              <p><a class="btn btn-lg btn-primary" href="#" role="button">点击查看</a></p>
	              -->
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

      <!-- Three columns of text below the carousel -->
      <div class="row">
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
          <h2>安全</h2>
          <p class="text-left">
            企业级服务器，安装企业级防火墙保护，结合每日备份机制确保数据安全。
          </p>
          <p><a class="btn btn-default" href="#" role="button">查看详情 &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
          <h2>专业</h2>
          <p class="text-left">
            拥有专业研究团队对数据进行分析，根据客户要求出具调查分析报告，为企业提供专业性的建议与对策。
          </p>
          <p><a class="btn btn-default" href="#" role="button">查看详情 &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
          <h2>便捷</h2>
          <p class="text-left">
            可通过微信、微博、QQ发布问卷，更方便快捷地收集数据。
          </p>
          <p><a class="btn btn-default" href="#" role="button">查看详情 &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
      </div><!-- /.row -->


      <!-- START THE FEATURETTES -->

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">什么是顾客满意度测评？</h2>
          <p class="lead">
            目的——客观评价顾客对服务的满意程度<br>
            实现——通过数学模型将顾客的主观感受量化成定量的指数<br>
            手段——问卷调查、统计分析<br>
            结果——形成满意度分析报告，分离出提升服务水平的关键因素
          </p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
          <h2 class="featurette-heading">满意度测评的目标</h2>
          <p class="lead">
            明确顾客的满意度（基本目标）<br>
            明确顾客对于产品和服务的核心关注点<br>
            发现自身和竞争企业的差异<br>
            发现产品和服务的缺陷<br>
            对需改进的因素区分重要等级<br>
            发掘顾客潜在的需求<br>
            建立满意度标准体系，持续改进
          </p>
        </div>
        <div class="col-md-5 col-md-pull-7">
          <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">满意度测评的作用</h2>
          <p class="lead">
            从部门或行业层面,顾客满意度测评能提供不同行业或部门之间的比较<br>
            从企业层面,顾客满意度测评为企业持续改进提供依据<br>
            从顾客层面,顾客满意度测评为消费者一个反映心声的通道
          </p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

      <!-- /END THE FEATURETTES -->

<%@include file="/webpage/context/bottom.jsp"%>
      