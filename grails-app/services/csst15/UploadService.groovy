package csst15

import javax.servlet.http.HttpServletRequest

class UploadService {

    def uploadFile(HttpServletRequest request, String field) {
        def reqFile = request.getFile("${field}")
        if (!reqFile?.empty) {
            InputStream file = reqFile?.inputStream
            byte[] bytes = file?.bytes
            return bytes
        }
    }
}
