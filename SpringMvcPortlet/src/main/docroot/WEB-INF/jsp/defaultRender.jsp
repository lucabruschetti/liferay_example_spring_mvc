<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<portlet:defineObjects />

<div id="pieChart" style="text-align: center"></div>

<script>

	var utenti = null;

	AUI().use(
		'liferay-portlet-url', 
		'aui-base', 
		'aui-io-deprecated', 
		function( A ) {
			var resourceURL = Liferay.PortletURL.createResourceURL();
   			resourceURL.setPortletId("springmvc_WAR_SpringMvcPortletportlet");
  			resourceURL.setResourceId("utenteResource");
  			resourceURL.setParameter("pattern","50");
  			resourceURL.setCopyCurrentRenderParameters(true);
   
   			console.log("resourceUrl = " + resourceURL.toString());
    
   			A.io.request( resourceURL.toString(), {
   				method: 'GET',
       			dataType: 'json',
       			on: {
           			success: function(event, id, obj) {
           				utenti = this.get('responseData');
               			drawPie();
           			},
           			failure: function (e) {
           				var message = this.get('responseData');
           				alert("Ajax Error : "+message);	
           			}
       			}
   			});
		}
	);


	function drawPie(){
		
		var pie = new d3pie("pieChart", {
			"header": {
				"title": {
					"text": "User Age distribution",
					"fontSize": 24,
					"font": "open sans"
				},
				"subtitle": {
					"text": "A full pie chart to show off label collision detection and resolution.",
					"color": "#999999",
					"fontSize": 12,
					"font": "open sans"
				},
				"titleSubtitlePadding": 9
			},
			"footer": {
				"color": "#999999",
				"fontSize": 10,
				"font": "open sans",
				"location": "bottom-left"
			},
			"size": {
				"canvasWidth": 590
			},
			"data": {
				"sortOrder": "value-desc",
				"content": utenti.utenti
			},
			"labels": {
				"outer": {
					"pieDistance": 32
				},
				"inner": {
					"hideWhenLessThanPercentage": 3
				},
				"mainLabel": {
					"fontSize": 11
				},
				"percentage": {
					"color": "#ffffff",
					"decimalPlaces": 0
				},
				"value": {
					"color": "#adadad",
					"fontSize": 11
				},
				"lines": {
					"enabled": true
				}
			},
			"misc": {
				"gradient": {
					"enabled": true,
					"percentage": 100
				}
			},
			"effects": {
				load: {
					effect: "default", // none / default
					speed: 1000
				},
				pullOutSegmentOnClick: {
					effect: "bounce", // none / linear / bounce / elastic / back
					speed: 300,
					size: 10
				},
				highlightSegmentOnMouseover: true,
				highlightLuminosity: -0.2
			},
			"callbacks": {
				onload: null,
				onMouseoverSegment: null,
				onMouseoutSegment: null,
				onClickSegment: function(info) {
					console.log(info);
				}
			}
		});
	}
	
</script>


