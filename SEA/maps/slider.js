function initSlider() {
  $("#slider").ionRangeSlider({
    type: "double",
    grid: true,
    from_fixed: false,
    min: 2014,
    max: 2019,
    from: 2014,
    to: 2015,
    prettify_enabled: false,
    min_interval: 1,
    max_interval: 1,
    step: 1,
    drag_interval: true
  });

  $("#slider").on("change", function() {
    var $this = $(this),
      value = $this.prop("value").split(";");
    currentYear = "y" + value[0];
    console.log("New slider value: " + currentYear);
    loadEuropeLayer();
  });
}

function setSliderValue(from, to) {
  var slider = $("#slider").data("ionRangeSlider");
  slider.update({
    from: from,
    to: to
  });
}
