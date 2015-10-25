var pageloadforfacebook;
function facebookUnilogin(page) {
	pageloadforfacebook = page;	
	connectFacebookEnquiry();
	FB.login(function(response) {	
			if (response.session) { } else {}
	}, {scope:'email,publish_stream'});  //read_stream//perms
	}
	function connectFacebookEnquiry(){
		FB.init({appId: $1('apiId').value, status: true, cookie: true, xfbml: true}); 		
		FB.Event.subscribe('auth.login', function(response) {	login(); });
		//FB.getLoginStatus(function(response) {	if (response.session) {	login(); } });  
    FB.getLoginStatus(function(response) {
        // the user is logged in and has authenticated your app
        if (response.status === 'connected') {
            fbResponse=response.status;
            login(fbResponse);
            var uid = response.authResponse.userID;
            var accessToken = response.authResponse.accessToken;
        } else if (response.status === 'not_authorized') {
            // the user is logged in to Facebook, 
            // but has not authenticated your app
            fbResponse=response.status;
            FB.Event.subscribe('auth.login', function(response) {
            login(fbResponse);});
        
        } else {
            // the user isn't logged in to Facebook.
            fbResponse=response.status;
        }
    });
	 }

	function login(){		
		FB.api('/me', function(response) {			
			fqlQuery();
		});
if(document.getElementById('enq_fb') !=null){
document.getElementById('enq_fb').style.display="none";
}
	}

function graphStreamPublish(){		
	  description =$1('description').value;
	  hrefLink = $1('hrefLink').value;
	  hrefTitle = $1('hrefTitle').value;
		 FB.api(
		{
			method: 'stream.publish',                    
			message: $1('message').value,
			attachment: {
				'name': $1('name').value,
				'caption': $1('caption').value,
				'description': (description),
				'href': hrefLink,
				'media':[{'type':'image','src':$1('fbkimg').value,'href':$1('mediahref').value}]                        
			},
			action_links: [
				{ text: hrefTitle, href: hrefLink }
			]                    
		},
		function(response) {
		});               
	}
function fqlQuery(){
     FB.api('/me', function(response) {
         var fname =response.first_name;
				 var lname = response.last_name;
				 var email = response.email;
				 var gender = response.gender;
         var fbId = response.id
				 var city=null;                         
				 var country=null;
        if(email != null && email!='undefined'){
       facebookRegister(fname,lname,email,gender,city,country,fbId,pageloadforfacebook);
       }else{
        // alert('Please try after sometime');
       }
     });
}//fqlQuery
function facebookRegister(fname,lname,email,gender,hometown,country,fbId,pageloadforfacebook){
var ajax=new sack();
ajax.requestFile = "/study/RegisterViaFacebook.html?fname="+fname+"&lname="+lname+"&email="+email+"&gender="+gender+"&hometown="+hometown+"&country="+country+"&fbId="+fbId+"&fbpagename="+pageloadforfacebook;
ajax.onCompletion = function(){getUserFBAutoLogin(ajax,fname);}	
ajax.runAJAX();
}
function getUserFBAutoLogin(ajax,fname){
var text=ajax.response;
    var c_data = get_cookie("u_enq");
    if(c_data != null) {
      var values = c_data.split("azw-wza");
	     var userstatus = values[2];
	     if(pageloadforfacebook == "header"){
        if(!userstatus){
		      graphStreamPublish();
	       }
     var outtext='<span>';
    if($1('countryflag').value=="true"){
      outtext+='<a href="#" onclick="countrydropdown("cntr");" class="abdr undr"><span class="dar"><span class="'+$1('countryDomain').value+'">'+$1('hmddtext').value+'</span></span></a>';
      }  
      if($('htmlContentFromCms').value!="null"){     
      outtext+=$('htmlContentFromCms').value;
      }
      outtext+='<em class="usr"> <strong>'+$1('hitext').value+', '+values[4]+'</strong>  -</em>';
      outtext+='<a class="abdr"  href="/study/user/profile.html">'+$1('mydetail').value+'</a>'
      outtext+='<a href="/study/logout.html" >'+$1('logoutId').value+'</a>';
       if($1('countryflag').value=="true"){
       outtext+='<div class="cntr" id="cntr" style="display:none"></div>';
       }
       outtext+=' </span>';
       $1('login').innerHTML=outtext;       
	     }else if(pageloadforfacebook =="enquiry" || pageloadforfacebook =="nonmasenquiry"){
        if(!userstatus){
		         graphStreamPublish();
	        }
      if(pageloadforfacebook =="nonmasenquiry"){   
      $1("fstname").value=values[4];
      $1("lstname").value=values[5];
      }else{
      $1("fstname").value=values[4];
      $1("lstname").value=values[5];
      }
	  $1("eMail").value = values[0];
      $1('eMail').readonly="true";
      document.location.reload(true)//abirami added for reloading
      if( values[1] != "" &&  values[1] != null){
       $1("yournationality").value = values[1];
      }
      $1('facebookloginbutton').style.display ='none';
		    $1('facebookloginOR').style.display ='none';
      $1('reg').innerHTML="";  
	    }else if(pageloadforfacebook =="enquirylogin"){
       var content=ajax.response.split("##");
       if(content[0]!=null && content[0]=='AAA'){
       if(content[1]!=null){
       window.location.href=content[1]
 }
 }
     }else if(pageloadforfacebook =="lightboxlogin"){
            if(!userstatus){
		      graphStreamPublish();
	       }
         if($1('previousurlforredirection')!=null){
        	 if($1('previousurlforredirection').value!=null){
        		 if($1('previousurlforredirection').value .indexOf("/registration")!=-1){
        		     	window.location.href=$1('servernamefacebook').value;
        		 }else{
     		     	window.location.href=$1('previousurlforredirection').value;
        		 }
        	 }else{
                 document.location.reload(true);
        	 }
         }else{
             document.location.reload(true);
         }
     }else if(pageloadforfacebook =="lightboxregister"){
    	window.location.href='http://'+$1('servernamefacebook').value;
            if(!userstatus){
		      graphStreamPublish();
	       }
       window.location.href='http://'+$1('sName').value+'/study/user/profile.html';
     }else if(pageloadforfacebook =="DPlogin"){
              document.location.reload(true);
     }else if(pageloadforfacebook =="Reviewlogin"){
              document.location.reload(true);
     }
     else if(pageloadforfacebook =="topnav"){
    	 if(text.indexOf("#@#@#@")!=-1){
    	      var weblinks = text.split("#@#@#@");
    	       if(weblinks[2]!=null){
    	                weblnk = weblinks[2];
    	                if(weblnk.indexOf("/registration")!=-1 || weblnk.indexOf("/loginhome")!=-1){
    	                	window.location.href="http://"+$1('servernamefacebook').value;
    	                }else{
    	                window.location.href= weblnk; 
    	                }
    	             }
    	     }
    	 else{
    		 if(text.indexOf("##")!=-1){
       	      var weblinks = text.split("##");
       	       if(weblinks[2]!=null){
       	                weblnk = weblinks[2];
       	             if(weblnk.indexOf("/registration")!=-1 || weblnk.indexOf("/loginhome")!=-1){
 	                	window.location.href="http://"+$1('servernamefacebook').value;
 	                }else{
       	                window.location.href= weblnk;  
       	             }
       	       }
       	     }
    	 }
     }
     // pageloadforfacebook ifelseblocks

   }//cdata
    if(text.indexOf("#@#@#@")!=-1){
      var weblinks = text.split("#@#@#@");
       if(weblinks[1]!=null){
                weblnk = weblinks[1];
                window.location.href= weblnk;  
             }
     }
	} //getUserFBAutoLogin  
function FacebookInviteFriends()
{
FB.init({
appId:$1('apiId').value,
cookie:true,
status:true,
xfbml:true
});
FB.ui({
method: 'apprequests',
message: 'Refer your friends Page',
max:10
},function(response){ friendslist(response);}
);
}

function friendslist(response){
 if (response && response.to) {
  if(response.to.length>0){
    showfacebookInviteSucess();
  }
 }
}//friendslist