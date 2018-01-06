package indi.joynic.naga;

/**
 * The very abstract class that call access method actually.
 *
 * @param <R> Result of the access return
 */
public abstract class AbstractPortalAccessorImpl<R> implements PortalAccessor {

    private AccessSubject<R> subject;

    protected AbstractPortalAccessorImpl(AccessSubject<R> subject) {
        this.subject = subject;
    }

    @Override
    public R access() {
        return subject.access();
    }

}
