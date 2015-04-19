package csst15

class AutoCompleteController {
    def autoCompleteService

    static allowedMethods = [
    ]

    def loadInterests() {
        render autoCompleteService.autoComplete(params)
    }
}
