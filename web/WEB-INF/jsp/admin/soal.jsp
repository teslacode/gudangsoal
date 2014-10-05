<%@include file="index.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            $(function(){
                $("#pertanyaanTable").dataTable({
                    "sPaginationType": "full_numbers",
                    "bJQueryUI": true,
                    "aoColumns": [
			null,
			null,
			null,
			{"bSearchable": false, 'bSortable': false}
                    ]
                });
                $("#save").click(function(){
                    $("#inputDescription").val($("#editor").html());
                });
                $("#pertanyaanTable").on('click', '.changeStatus', function() { 
                    var id = $(this).parents("tr").find(".id").text().trim();
                    var classStatus = $(this).parents("tr").find(".status");
                    var status = $(this).parents("tr").find(".status").text().trim();
                    $("#dialog-confirm").dialog(
                    {
                        modal: true,
                        draggable: false,
                        resizable: false,
                        buttons: {
                            "Ya": function(){
                                $.post("${fullpath}",{id: id, status: status},function(data){
                                    classStatus.html(data);
                                });
                                $(this).dialog("close");
                            },
                            "Tidak": function(){
                                $(this).dialog("close");
                            }
                        }
                    });
                });
                function initToolbarBootstrapBindings() {
                    var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier', 
                        'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
                        'Times New Roman', 'Verdana'],
                    fontTarget = $('[title=Font]').siblings('.dropdown-menu');
                    $.each(fonts, function (idx, fontName) {
                        fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
                    });
                    $('a[title]').tooltip({container:'body'});
                    $('.dropdown-menu input').click(function() {return false;})
                    .change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
                    .keydown('esc', function () {this.value='';$(this).change();});
                    
                    $('[data-role=magic-overlay]').each(function () { 
                        var overlay = $(this), target = $(overlay.data('target')); 
                        overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
                    });
                };
                function showErrorAlert (reason, detail) {
                    var msg='';
                    if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
                    else {
                        console.log("error uploading file", reason, detail);
                    }
                    $('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
                        '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
                };
                initToolbarBootstrapBindings();  
                $('#editor').wysiwyg({ fileUploadError: showErrorAlert} );
                window.prettyPrint && prettyPrint();
            });
        </script>
    </head>
    <body>
        <div id="dialog-confirm" hidden="true" title="Konfirmasi?">Apakah anda yakin?</div>
        <div class="col-md-12">
            <div class="col-md-12">
                <c:forEach items="${listRfTingkat}" var="rfTingkat">
                    <div class="col-md-2">
                        <a href="${fullPath}/${rfTingkat.id}">${rfTingkat.description}</a>
                    </div>
                </c:forEach>
                <c:forEach items="${listRfKelas}" var="rfKelas">
                    <div class="col-md-2">
                        <a href="${fullPath}/${rfKelas.id}">${rfKelas.description}</a>
                    </div>
                </c:forEach>
                <c:forEach items="${listRfPelajaran}" var="rfPelajaran">
                    <div class="col-md-2">
                        <a href="${fullPath}/${rfPelajaran.id}">${rfPelajaran.description}</a>
                    </div>
                </c:forEach>
                <c:if test="${!empty listPertanyaan}">
                    <div class="panel panel-default panel-body">
                        <div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
                            <div class="btn-group">
                                <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font"><i class="fa fa-font"></i><b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                </ul>
                            </div>
                            <div class="btn-group">
                                <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font Size"><i class="fa fa-text-height"></i>&nbsp;<b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
                                    <li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
                                    <li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
                                </ul>
                            </div>
                            <div class="btn-group">
                                <a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="fa fa-bold"></i></a>
                                <a class="btn" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a>
                                <a class="btn" data-edit="strikethrough" title="Strikethrough"><i class="fa fa-strikethrough"></i></a>
                                <a class="btn" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i class="fa fa-underline"></i></a>
                            </div>
                            <div class="btn-group">
                                <a class="btn" data-edit="insertunorderedlist" title="Bullet list"><i class="fa fa-list-ul"></i></a>
                                <a class="btn" data-edit="insertorderedlist" title="Number list"><i class="fa fa-list-ol"></i></a>
                                <a class="btn" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i class="fa fa-outdent"></i></a>
                                <a class="btn" data-edit="indent" title="Indent (Tab)"><i class="fa fa-indent"></i></a>
                            </div>
                            <div class="btn-group">
                                <a class="btn" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i class="fa fa-align-left"></i></a>
                                <a class="btn" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i class="fa fa-align-center"></i></a>
                                <a class="btn" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i class="fa fa-align-right"></i></a>
                                <a class="btn" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i class="fa fa-align-justify"></i></a>
                            </div>
                            <div class="btn-group">
                                <a class="btn dropdown-toggle" data-toggle="dropdown" title="Hyperlink"><i class="fa fa-link"></i></a>
                                <div class="dropdown-menu input-append">
                                    <input class="span2" placeholder="URL" type="text" data-edit="createLink"/>
                                    <button class="btn" type="button">Add</button>
                                </div>
                                <a class="btn" data-edit="unlink" title="Remove Hyperlink"><i class="fa fa-cut"></i></a>
                            </div>
                            <div class="btn-group">
                                <a class="btn" title="Insert picture (or just drag & drop)" id="pictureBtn"><i class="fa fa-picture-o"></i></a>
                                <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
                            </div>
                            <div class="btn-group">
                                <a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="fa fa-undo"></i></a>
                                <a class="btn" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="fa fa-repeat"></i></a>
                            </div>
                        </div>
                        <form:form method="POST" commandName="pertanyaan">
                            <div id="editor"></div>
                            <br/>
                            <form:hidden id="inputDescription" path="description"></form:hidden>
                                <button id="save" name="save" class="btn btn-primary">Save</button>
                                <br/>
                        </form:form>
                    </div>
                    <table id="pertanyaanTable" class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>
                                    Id
                                </th>
                                <th>
                                    Pertanyaan
                                </th>
                                <th>
                                    Status
                                </th>
                                <th>
                                    Fungsi
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listPertanyaan}" var="pertanyaan">
                                <tr>
                                    <td class="id">${pertanyaan.id}</td>
                                    <td>
                                        ${pertanyaan.description}
                                    </td>
                                    <td class="status">
                                        ${pertanyaan.status}
                                    </td>
                                    <td>
                                        <a class="changeStatus">Change Status</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </body>
</html>