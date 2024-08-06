package use_case.joinEvent;


public class joinEventInteractor implements joinEventInputBoundary {
    final  joinEventDataAccessInterface joinEventDataAccessInterface;
    final joinEventOutputBoundary joinEventPresenter;


    public joinEventInteractor(joinEventDataAccessInterface joinEventDataAccessInterface, joinEventOutputBoundary joinEventPresenter) {
        this.joinEventDataAccessInterface = joinEventDataAccessInterface;
        this.joinEventPresenter = joinEventPresenter;
    }


    @Override
    public void execute(joinEventInputData joinEventInputData) {
        joinEventDataAccessInterface.addParticipant(joinEventInputData.getEventName(), joinEventInputData.getUsername());
        joinEventOutputData joinEventOutputData = new joinEventOutputData(joinEventInputData.getUsername());
        joinEventPresenter.prepareJoinEventSuccessView(joinEventOutputData);

    }
}
