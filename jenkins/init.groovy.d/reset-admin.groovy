import jenkins.model.*
import hudson.security.*

def instance = Jenkins.get()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("vaidehi", "Vaidehi@123")
instance.setSecurityRealm(hudsonRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.save()
