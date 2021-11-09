function start() {
    const paramsString = document.location.search; // ?page=4&limit=10&sortby=desc
    const searchParams = new URLSearchParams(paramsString);
    const str = searchParams.toString().split('=');
    const str2 = str[1].split('.');
    return str2;
}