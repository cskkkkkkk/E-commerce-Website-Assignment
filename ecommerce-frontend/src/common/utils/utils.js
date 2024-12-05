/**
 * @brief 获得图片的前缀，如果是相对路径那就拼接，绝对路径就返回
 * @param url 图片的前缀
 * @returns {*|string}
 */
export const prefix = (url) => {
    if (url && url.startsWith('http')) {
        return url
    } else {
        url = `http://backend-api-01.newbee.ltd${url}`
        return url
    }
}

export const getLocal = (name) => {
    return localStorage.getItem(name)
}

export const setLocal = (name, value) => {
    localStorage.setItem(name, value)
}