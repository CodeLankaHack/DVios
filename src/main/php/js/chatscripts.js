/**
 * Created by dulaj on 5/12/16.
 */

(function () {
    var Message;
    Message = function (arg) {
        this.text = arg.text, this.message_side = arg.message_side;
        this.draw = function (_this) {
            return function () {
                var $message;
                $message = $($('.message_template').clone().html());
                $message.addClass(_this.message_side).find('.text').html(_this.text);
                $('.messages').append($message);
                return setTimeout(function () {
                    return $message.addClass('appeared');
                }, 0);
            };
        }(this);
        return this;
    };
    $(function () {
        var getMessageText, message_side, sendMessage;
        message_side = 'right';
        getMessageText = function () {
            var $message_input;
            $message_input = $('.message_input');
            return $message_input.val();
        };
        sendMessage = function (text, isLeft) {
            var $messages, message;
            if (text.trim() === '') {
                return;
            }
            $('.message_input').val('');
            $messages = $('.messages');
            // message_side = message_side === 'left' ? 'right' : 'left';
            if(isLeft){
                message_side = "left";
            }
            else{
                message_side = 'right';
            }
            message = new Message({
                text: text,
                message_side: message_side
            });
            message.draw();

            // Send message to AI
            if(!isLeft){
                $url = '/Connector.php?msg=' + text;
                $.get($url, function (response) {
                    processResponse(response);
                });
            }

            return $messages.animate({ scrollTop: $messages.prop('scrollHeight') }, 300);
        };
        processResponse = function (response) {
            setTimeout(function () {
                setBotState(response['messageType']);

                if(response['messageType']=='Comparision'){
                    sendMessage(response['message'], true);
                    $('#display-info').empty();
                    $('#display-info').append("<div style='display: none;' class=\"panel panel-yellow\"><div class=\"panel-heading\"><h3 class=\"panel-title\">Item</h3></div><div class=\"panel-body\">Product name: " + response['mainItem'] + "<br>Brand: " + response['brand'] + "<br>Price: Rs. " + response['price'] + "</div></div>");
                    $('.panel-yellow').fadeIn(1000);
                }
                else if(response['messageType']=='Location'){
                    sendMessage(response['message'], true);
                }
                else if(response['messageType']=='Price'){
                    sendMessage(response['message'], true);
                    $('#display-info').empty();
                    $('#display-info').append("<div style='display: none;' class=\"panel panel-success\"><div class=\"panel-heading\"><h3 class=\"panel-title\">Item</h3></div><div class=\"panel-body\">Product name: " + response['mainItem'] + "<br>Brand: " + response['brand'] + "<br>Price: Rs. " + response['price'] + "</div></div>");
                    $('.panel-success').fadeIn(1000);
                }
                else if(response['messageType']=='PriceList'){
                    $('#display-info').empty();
                    var i=0;
                    for(i=0;i<response['related'].length;i++){
                        $('#display-info').append("<div style='display: none;' class=\"panel panel-info\"><div class=\"panel-heading\"><h3 class=\"panel-title\">Item</h3></div><div class=\"panel-body\">Product name: " + response['related'][i]['mainItem'] + "<br>Brand: " + response['related'][i]['brand'] + "<br>Price: Rs. " + response['related'][i]['price'] + "</div></div>");
                        $('.panel-info').fadeIn(1000);
                    }
                }
            }, 1000);

        };

        setBotState = function (state) {
            if(state=='Comparision'){
                $('.animation-info').hide();
                $('#display-image').hide()
                $('#display-info').show();
            }
            else if(state=='Location'){
                $('.animation-info').hide();
                $('#display-image').show();
                $('#display-info').show();
            }
            else if(state=='Price'){
                $('.animation-info').hide();
                $('#display-image').hide()
                $('#display-info').show();
            }
            else if(state=='PriceList'){
                $('.animation-info').hide();
                $('#display-image').hide()
                $('#display-info').show();
            }
            else if(state=='Reset'){
                $('.animation-info').show();
                $('#display-image').hide()
                $('#display-info').hide();
            }
        };

        $('.send_message').click(function (e) {
            return sendMessage(getMessageText(), false);
        });
        $('.message_input').keyup(function (e) {
            if (e.which === 13) {
                return sendMessage(getMessageText(), false);
            }
        });
    });
}.call(this));