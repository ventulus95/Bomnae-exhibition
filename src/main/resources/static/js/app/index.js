var buttonJS = {
    init: function(){
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var formData = new FormData();
        formData.append('file', $('#file').get(0).files[0]);
        formData.append('title', $('#title').val());
        formData.append('content', $('#content').val());
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            // dataType: 'json',
            contentType: false,
            processData: false,
            // contentType:'application/json; charset=utf-8',
            data: formData
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        var data ={
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();
        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        var id = $('#id').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

var commentJS = {
    init: function(){
        var _this = this;
        $('#btn-comment-save').on('click', function(){
            _this.save();
        });
    },
    save : function () {
        var data = {
            comment: $('#comment').val()
        };
        var num= $(location).attr('pathname').split('/')[3];

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts/'+num+'/comment',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/post/update/'+num;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

var gusetbookJS = {
    init: function(){
        var _this = this;
        $('#btn-guestbook-save').on('click', function(){
            _this.save();
        });
    },
    save : function () {
        var data = {
            comment: $('#comment').val()
        };
        $.ajax({
            type: 'POST',
            url: '/guestbook',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/guestbook';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};


buttonJS.init();
commentJS.init();
gusetbookJS.init();

$(document).ready(function () {
    $('#modal-default').modal('show');
});
