package Structural_Design_Pattern.Fascade_Design_Pattern;

class Utitlity{
    public static void  simulateDelay(int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class VersionControlSystem {
    public void pullLatestChanges(String branch) {
        System.out.println("VCS: Pulling latest changes from '" + branch + "'...");
        Utitlity.simulateDelay(1000);
        System.out.println("VCS: Pull complete.");
    }
}

class BuildSystem {
    public boolean compileProject() {
        System.out.println("BuildSystem: Compiling project...");
        Utitlity.simulateDelay(2000);
        System.out.println("BuildSystem: Build successful.");
        return true;
    }

    public String getArtifactPath() {
        String path = "target/myapplication-1.0.jar";
        System.out.println("BuildSystem: Artifact located at " + path);
        return path;
    }
}

class TestingFramework {
    public boolean runUnitTests() {
        System.out.println("Testing: Running unit tests...");
        Utitlity.simulateDelay(1500);
        System.out.println("Testing: Unit tests passed.");
        return true;
    }

    public boolean runIntegrationTests() {
        System.out.println("Testing: Running integration tests...");
        Utitlity.simulateDelay(3000);
        System.out.println("Testing: Integration tests passed.");
        return true;
    }
}

class DeploymentTarget {
    public void transferArtifact(String artifactPath, String server) {
        System.out.println("Deployment: Transferring " + artifactPath + " to " + server + "...");
        Utitlity.simulateDelay(1000);
        System.out.println("Deployment: Transfer complete.");
    }

    public void activateNewVersion(String server) {
        System.out.println("Deployment: Activating new version on " + server + "...");
        Utitlity.simulateDelay(500);
        System.out.println("Deployment: Now live on " + server + "!");
    }
}

class DeploymentFascade{
    private VersionControlSystem versionControlSystem=new VersionControlSystem();
    private BuildSystem buildSystem=new BuildSystem();
    private TestingFramework testingFramework=new TestingFramework();
    private DeploymentTarget deploymentTarget=new DeploymentTarget();

    public boolean deploy(String branch,String serverAddress){
        System.out.println("\nFACADE: --- Initiating FULL DEPLOYMENT for branch: " + branch + " to " + serverAddress + " ---");
        boolean success = true;

        try{
            versionControlSystem.pullLatestChanges(branch);
            if (!buildSystem.compileProject()) {
                System.err.println("FACADE: DEPLOYMENT FAILED - Build compilation failed.");
                return false;
            }
            String artifactPath = buildSystem.getArtifactPath();

            if (!testingFramework.runUnitTests()) {
                System.err.println("FACADE: DEPLOYMENT FAILED - Unit tests failed.");
                return false;
            }

            if (!testingFramework.runIntegrationTests()) {
                System.err.println("FACADE: DEPLOYMENT FAILED - Integration tests failed.");
                return false;
            }
            deploymentTarget.transferArtifact(artifactPath, serverAddress);
            deploymentTarget.activateNewVersion(serverAddress);

            System.out.println("FACADE: APPLICATION DEPLOYED SUCCESSFULLY to " + serverAddress + "!");
        } catch (Exception e){
            System.err.println("FACADE: DEPLOYMENT FAILED - An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            success = false;
        }
        return success;
    }
}

public class Deployment_Subsystem {
    public static void main(String[] args) {
        DeploymentFascade deploymentFacade = new DeploymentFascade();

        // Deploy to production
        deploymentFacade.deploy("main", "prod.server.example.com");

        // Deploy a feature branch to staging
        System.out.println("\n--- Deploying feature branch to staging ---");
        deploymentFacade.deploy("feature/new-ui", "staging.server.example.com");
    }
}
