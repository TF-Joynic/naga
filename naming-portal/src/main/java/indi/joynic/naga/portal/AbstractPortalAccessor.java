package indi.joynic.naga.portal;

/**
 * The very abstract class that call access method actually.
 *
 * @param <R> Result of the access return
 */
public abstract class AbstractPortalAccessor<R> implements PortalAccessor {

    private AccessSubject<R> subject;

    protected AbstractPortalAccessor(AccessSubject<R> subject) {
        this.subject = subject;
    }

    @Override
    public R access() {
        return subject.access();
    }

}
