$("#showForm").on("click", () => {
    $("#form").show();
    $("#showForm").hide();
    $("#hideForm").show();
});

$("#hideForm").on("click", () => {
    $("#form").hide();
    $("#showForm").show();
    $("#hideForm").hide();
});