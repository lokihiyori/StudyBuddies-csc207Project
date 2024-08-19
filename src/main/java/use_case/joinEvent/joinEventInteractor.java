package use_case.joinEvent;

/**
 * The JoinEventInteractor class implements the JoinEventInputBoundary interface
 * and handles the business logic for a user joining an event. It interacts with the
 * data access interface to add the user as a participant in the event and communicates
 * with the output boundary to present the result of the join event process.
 */
public class joinEventInteractor implements joinEventInputBoundary {
    final  joinEventDataAccessInterface joinEventDataAccessInterface;
    final joinEventOutputBoundary joinEventPresenter;

    /**
     * Constructs a new JoinEventInteractor with the specified data access interface
     * and output boundary.
     *
     * @param joinEventDataAccessInterface the data access interface used to manage event participation.
     * @param joinEventPresenter the output boundary to handle the result of the join event process.
     */
    public joinEventInteractor(joinEventDataAccessInterface joinEventDataAccessInterface, joinEventOutputBoundary joinEventPresenter) {
        this.joinEventDataAccessInterface = joinEventDataAccessInterface;
        this.joinEventPresenter = joinEventPresenter;
    }

    /**
     * Executes the process of a user joining an event using the provided input data.
     * The process involves adding the user as a participant to the event and preparing
     * the success view through the output boundary.
     *
     * @param joinEventInputData the data required for a user to join an event.
     */
    @Override
    public void execute(joinEventInputData joinEventInputData) {
        joinEventDataAccessInterface.addParticipant(joinEventInputData.getEventName(), joinEventInputData.getUsername());
        joinEventOutputData joinEventOutputData = new joinEventOutputData(joinEventInputData.getUsername());
        joinEventPresenter.prepareJoinEventSuccessView(joinEventOutputData);

    }

    /**
     * Returns the data access interface associated with this interactor.
     *
     * @return the JoinEventDataAccessInterface instance used by this interactor.
     */
    public Object getFileEventDataAccessObject() {
        return joinEventDataAccessInterface;
    }

    /**
     * Returns the output boundary associated with this interactor.
     *
     * @return the JoinEventOutputBoundary instance used by this interactor.
     */
    public Object getPresenter() {
        return joinEventPresenter;
    }
}
