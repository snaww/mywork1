(function (){
    var $content = $('#share-options').detach();//remove modal from page
    $('#share').on('click',function (){//click handler to open modal
        modal.open({content:$content,width:340,height:300});
    });
}());