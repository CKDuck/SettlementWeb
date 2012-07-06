function doDeleteRateConfirm(rateId, domainName, productName) {
    confirmDialog = new ConfirmDialog();
    confirmDialog.showMessage(
        'Delete rate ' + domainName + ' (' + productName + ')?',
        function(rateId) {
            $.ajax({
                type: 'POST',
                url: '/rates/delete',
                data : 'rateId=' + rateId,
                success: function(oData) {
                    location.reload();
                }
            });
        },
        rateId
    );
}

function ConfirmDialog() {
    this.showMessage = function(message, callback, argument) {

        var $dialog = $('<div></div>')
            .html(message)
            .dialog({
                modal: true,
                closeOnEscape: true,
                buttons: {
                    Yes: function() {
                        if (callback && typeof(callback) === "function") {
                            if (argument == 'undefined') {
                                callback();
                            } else {
                                callback(argument);
                            }
                        }

                        $(this).dialog("close");
                    },

                    No: function() {
                        $(this).dialog("close");
                    }
                }
            });
        $dialog.dialog("open");
    }
}
