package codes.speed.collagenews.ui.dashboard;

public class DashboardViewModel {
    private String name;

    public DashboardViewModel() {
    }

    public DashboardViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}