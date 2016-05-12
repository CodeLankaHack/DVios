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
                sendMessage(response['message'], true);

                if(response['messageType']=='Comparision'){
                    $('.panel').fadeIn(500);
                    $('.panel').empty();
                    $('.panel').append("<div class=\"panel-heading\"><h3 class=\"panel-title\">Cheapest Item</h3></div><div class=\"panel-body\">" + response['mainItem'] + "<br>Rs. " + response['price'] + "</div>");
                }
                else if(response['messageType']=='Location'){

                }
                else if(response['messageType']=='Price'){

                }
            }, 1000);

        };
        $('.send_message').click(function (e) {
            return sendMessage(getMessageText(), false);
        });
        $('.message_input').keyup(function (e) {
            if (e.which === 13) {
                return sendMessage(getMessageText(), false);
            }
        });
        // setInterval(function () {
        //     $.get('/message/get', function (data) {
        //         if(data.length>0){
        //             i=0;
        //             if(i<data.length){
        //                 $('.message_input').val(data);
        //                 setTimeout(function () {
        //                     $('.message_input').val('');
        //                     sendMessage(data, true);
        //                 }, 500);
        //             }
        //         }
        //     })
        // }, 1000);
    });
}.call(this));