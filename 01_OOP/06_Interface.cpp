#include <iostream>

using namespace std;

class NotificationService{
    public:
        virtual void send(string rec,string mes)=0;

        virtual ~NotificationService()=0;
};

NotificationService::~NotificationService() {
    cout << "NotificationService destroyed" << endl;
}

class Email : public NotificationService{
    public:
        void send(string recepient, string message) override {
            cout<<"Sending notification through Email"<<endl;
            cout<<recepient<<" "<<message<<endl;
        }

        ~Email(){
            cout<<"Destructuring EMAIL"<<endl;
        };
};

class SMS : public NotificationService {
    public:
        void send(string recepient, string message) override {
            cout<<"Sending notification through SMS"<<endl;
            cout<<recepient<<" "<<message<<endl;
        }

        ~SMS(){
            cout<<"Destructuring SMS"<<endl;
        }
};

class AlertService{
    private:
        NotificationService* notificationService;
    public:
        AlertService(NotificationService* notificationService): notificationService(notificationService){}

        void triggerAlert(string recepient,string message){
            if(notificationService!=nullptr){
                notificationService->send(recepient,message);
            }
        }

        ~AlertService(){
            delete notificationService;
        }
};

int main(){
    NotificationService* n = new Email();
    AlertService* alertService = new AlertService(n);

    alertService->triggerAlert("amish@gmail","Hi there is some issue in prod");

    delete alertService;

    NotificationService* s=new SMS();
    alertService = new AlertService(s);
    alertService->triggerAlert("987654321","Hi there is some issue in prod");

    delete alertService;
}
