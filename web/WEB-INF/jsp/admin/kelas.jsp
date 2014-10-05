<%@include file="index.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            $(document).ready(function () {
                $("#reset").click(function(e){
                    e.preventDefault();
                    $("#tingkatId").val("");
                    $("#id").val("");
                    $("#id").removeAttr("readonly");
                    $("#description").val("");
                    $("#urutan").val("");
                });
                $('#kelasForm').validate(
                {
                   rules: {
                        id: {
                            required: true
                        },
                        tingkatId: {
                            required: true
                        }
                    },
                    highlight: function(element) {
                        $(element).closest('.form-group').addClass('has-error');
                    },
                    success: function(element) {
                        $(element).closest('.form-group').removeClass('has-error');
                        $(element).remove();
                    } 
                });
                $("#kelasTable").dataTable({
                    "sPaginationType": "full_numbers",
                    "bJQueryUI": true
                });
                $(".ubah").click(function(){
                    $("#tingkatId").val($(this).parents("tr:first").find(".tingkatId").text().trim());
                    $("#id").val($(this).parents("tr:first").find(".id").text().trim());
                    $("#id").attr("readonly",true);
                    $("#description").val($(this).parents("tr:first").find(".description").text().trim());
                    $("#urutan").val($(this).parents("tr:first").find(".urutan").text().trim());
                });
                $(".hapus").click(function(){
                    var id = $(this).parents("tr").find(".id").text().trim();
                    var tr = $(this).closest("tr");
                    $("#dialog-confirm").dialog(
                    {
                        modal: true,
                        draggable: false,
                        resizable: false,
                        buttons: {
                            "Ya": function(){
                                $.post("${fullpath}",{id: id, hapus: true},function(){
                                    tr.hide('slow',function(){ tr.remove()});
                                });
                                $(this).dialog("close");
                            },
                            "Tidak": function(){
                                $(this).dialog("close");
                            }
                        }
                    });
                });
            });
        </script>
    </head>
    <body>
        <div id="dialog-confirm" hidden="true" title="Konfirmasi?">Apakah anda yakin?</div>
        <div class="col-md-12">
            <form:form id="kelasForm" method="POST" commandName="rfKelas" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-xs-2">Tingkat</label>
                    <div class="col-xs-10">
                        <form:select path="tingkatId" class="form-control">
                            <form:options items="${listRfTingkat}" itemValue="id" itemLabel="description" />
                        </form:select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2">Id</label>
                    <div class="col-xs-10">
                        <form:input path="id" maxlength="10" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2">Description</label>
                    <div class="col-xs-10">
                        <form:input path="description" maxlength="100" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2">Urutan</label>
                    <div class="col-xs-10">
                        <form:input path="urutan" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-2 col-xs-10">
                        <form:button name="save" class="btn btn-primary">Save</form:button>
                        &nbsp;
                        <form:button id="reset" name="reset" class="btn btn-default">Reset</form:button>
                    </div>
                </div>
            </form:form>
            <table id="kelasTable" class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>
                            Tingkat
                        </th>
                        <th>
                            Id
                        </th>
                        <th>
                            Description
                        </th>
                        <th>
                            Urutan
                        </th>
                        <th>
                            Fungsi
                        </th>
                        <th hidden="true"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listRfKelas}" var="rfKelas">
                        <tr>
                            <td class="tingkat">
                                ${rfKelas.rfTingkat.description}
                            </td>
                            <td class="id">
                                ${rfKelas.id}
                            </td>
                            <td class="description">
                                ${rfKelas.description}
                            </td>
                            <td class="urutan">
                                ${rfKelas.urutan}
                            </td>
                            <td>
                                <a class="ubah">
                                    Ubah
                                </a>
                                &nbsp;&nbsp;
                                <a class="hapus">Hapus</a>
                            </td>
                            <td hidden="true" class="tingkatId">
                                ${rfKelas.rfTingkat.id}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
