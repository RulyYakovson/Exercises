function volume_sphere() {
    let volume;
    let radius = document.getElementById('radius');
    if (radius) {
        radius = Math.abs(radius.value);
        volume = (4/3) * Math.PI * Math.pow(radius, 3);
        volume = volume.toFixed(4);
        volumeElem = document.getElementById('volume');
        if (volumeElem) {
            volumeElem.value = volume;
        }
    }
}
