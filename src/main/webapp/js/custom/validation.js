if (typeof (Common) == "undefined") var Common = {};

(function($) {
	Common.Validation = function() {
		this.initialize.apply(this, arguments);
	};

	Common.Validation.prototype = {
		initialize : function(hash) {

		},

		nonValueCheck : function(id, content, remove, THIS) {
			var flag = true;
			var $id = $('#' + id);
			if (id == null && THIS != undefined) $id = THIS;

			if ($id.val().trim() == '') {
				if ($id.attr('type') == 'text' || $id.attr('type') == 'password') rUtils.messageForm(400, 'Please enter the '+content);
				else rUtils.messageForm(400, 'Please select the ' + content);
				$id.focus();
				if (remove) $id.val('');
				flag = false;
			}

			return flag;
		},

		nonValueCheckTA : function(id, content, remove, THIS) {
			var flag = true;
			var $id = $('#' + id);
			if (id == null && THIS != undefined) $id = THIS;

			if ($id.val().trim() == '') {
				alert('Please enter the ' + content);
				$id.focus();
				if (remove) $id.val('');
				flag = false;
			}
			return flag;
		},

		onlyNumCheck : function(id, content, remove) {
			var flag = true;
			if (/[^0-9]/g.test($('#' + id).val())) {
				alert("The " + content + " string may contain only numbers.");
				$('#' + id).focus();
				if (remove) $('#' + id).val('');
				flag = false;
			}
			return flag;
		},

		userIdCheck : function(id, content, remove) {
			var flag = true;
			if (!/^[A-Z|a-z][A-Za-z0-9\_]{4,}$/.test($('#' + id).val())) {
				alert('The ' + content + ' string is case-sensitive and may contain only alphabets, numbers, and "_" (underbar) with at least more than 5 characters.');
				$('#' + id).focus();
				if (remove) $('#' + id).val('');
				flag = false;
			}
			return flag;
		},

		passwordCheck : function(id, content, remove) {
			var flag = true;
			var tNum = 0, tUC = 0, tLC = 0, tUS = 0;
			var str = $('#' + id).val();

			if (str.length < 9) {
				alert('The password must contain at least more than 9 digits.');
				$('#' + id).focus();
				if (remove) $('#' + id).val('');
				flag = false;
			}

			if (flag) {
				for (var i = 0; i < str.length; i++) {
					if (48 <= str.charCodeAt(i) && str.charCodeAt(i) <= 57) tNum = 1;
					if (65 <= str.charCodeAt(i) && str.charCodeAt(i) <= 90) tUC = 1;
					if (97 <= str.charCodeAt(i) && str.charCodeAt(i) <= 122) tLC = 1;
					if (str.charCodeAt(i) == 33 || (35 <= str.charCodeAt(i) && str.charCodeAt(i) <= 38) || str.charCodeAt(i) == 64 || str.charCodeAt(i) == 94) tUS = 1;
				}

				if (!(tNum && (tUC || tLC) && tUS)) {
					alert('The password must be in English uppercase and lowercase letters,\n numbers and special characters in a combination of three or more.');
					$('#' + id).focus();
					if (remove) $('#' + id).val('');
					flag = false;
				}
			}

			return flag;
		},

		phoneCheck : function(id, content, remove) {
			var flag = true;
			if (!/^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/.test($('#' + id).val())) {
				alert('Please enter a phone number in valid format.\n (c. f. 010-1234-1234)');
				$('#' + id).focus();
				if (remove) $('#' + id).val('');
				flag = false;
			}
			return flag;
		},
		
		officePhoneCheck : function(id, content, remove) {
			var flag = true;
			if (!/^\d{2,3}-\d{3,4}-\d{4}$/.test($('#' + id).val())) {
				alert('Please enter a office phone number in valid format.\n (c. f. 02-123-1234)');
				$('#' + id).focus();
				if (remove) $('#' + id).val('');
				flag = false;
			}
			return flag;
		},

		emailCheck : function(id, content, remove) {
			var flag = true;
			if (!/^[\w\-]+@(?:(?:[\w\-]{2,}\.)+[a-zA-Z]{2,})$/.test($('#' + id).val())) {
				alert('Please enter an e-mail address in valid format.');
				$('#' + id).focus();
				if (remove) $('#' + id).val('');
				flag = false;
			}
			return flag;
		},

		ipCheck : function(id, content, remove) {
			if (!/^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$/.test($('#' + id).val())) {
				alert('Please enter a valid IP address.');
				$('#' + id).focus();
				if (remove) $('#' + id).val('');
				flag = false;
			}
			return flag;
		},

		byteCheck : function(id, maxByte, THIS) {
			var flag = true;
			var $id = $('#' + id);
			if (id == null && THIS != undefined) $id = THIS;
			var str = $id.val();
			var str_len = str.length;

			var rbyte = 0;
			var one_char = '';

			for (var i = 0; i < str_len; i++) {
				one_char = str.charAt(i);
				if (escape(one_char).length > 4) {
					rbyte += 3; // 한글3Byte
				} else {
					rbyte++; // 영문 등 나머지 1Byte
				}
			}

			if (rbyte > maxByte) {
				//alert("한글 " + Math.floor((maxByte / 3)) + "자 / 영문 " + maxByte + "자를 초과 입력할 수 없습니다.");
				
				alert("The number of characters can not be over " + maxByte + " letters for alphabets, " + Math.floor((maxByte / 3)) + " letters for Korean characters.");
				$('#' + id).focus();
				flag = false;
			}
			return flag;
		},

		specialCharCheck : function(id, content, remove, THIS) {
			var flag = true;
			var $id = $('#' + id);
			if (id == null && THIS != undefined) $id = THIS;
			if (!/^[A-Z|a-z][A-Za-z0-9\_]+$/.test($id.val())) {
				alert(content + ' must be a combination of case-sensitive and numbers.');
				$id.focus();
				if (remove) $id.val('');
				flag = false;
			}
			return flag;
		},

		searchCheck : function(id, content, remove) {
			var flag = true;
			if ($('#' + id).val().trim() != '' && !/^[A-Za-z0-9\_\-]+$/.test($('#' + id).val())) {
				alert(content + ' must be a combination of case-sensitive or numbers');
				$('#' + id).focus();
				if (remove) $('#' + id).val('');
				flag = false;
			}
			return flag;
		},

		numAndEngCheck : function(id, content, remove, THIS) {
			var flag = true;
			var $id = $('#' + id);
			if (id == null && THIS != undefined) $id = THIS;

			if (!/^[A-Za-z0-9]+$/.test($id.val())) {
				alert(content + ' must be a combination of case-sensitive and numbers.');
				$id.focus();
				if (remove) $id.val('');
				flag = false;
			}
			return flag;
		}
	};
})(jQuery);

var rValidation = new Common.Validation();