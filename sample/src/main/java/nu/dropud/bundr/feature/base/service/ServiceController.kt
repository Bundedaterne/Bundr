package nu.dropud.bundr.feature.base.service


interface ServiceController<T : ServiceFacade> {

    fun attachService(service: T)

    fun detachService()

    fun getService(): T?

}