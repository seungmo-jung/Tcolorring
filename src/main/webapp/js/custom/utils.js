if (typeof (Common) == "undefined") var Common = {};

(function($) {
	
});

(function($) {
	Common.Utils = function() {
		this.initialize.apply(this, arguments);
	};

	Common.Utils.prototype = {
		initialize : function(hash) {
		},

		getHtml : function(url) {
			url = $('#contextPath').val() + '/html/' + url + '.html';
			var html = '';

			$.ajax({
				type : 'get',
				url : url,
				dataType : 'html',
				async : false,
				success : function(r) {
					if (r.indexOf('<body>') != -1) {
						html = r.split('<body>')[1].split('</body>')[0];
					}
				}
			});

			return html;
		}
	};
	
})(jQuery);

var rUtils = new Common.Utils();

String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/gi, "");
};