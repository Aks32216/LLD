/**
 * ============================================================
 *  EXERCISE 1 — Library Management System  [Easy / Medium]
 * ============================================================
 *
 * CONCEPTS TESTED:
 *   - Classes & Objects
 *   - Access Modifiers (public / private / protected)
 *   - Constructors (parameterized + member initializer list)
 *   - Single Inheritance (public)
 *
 * ─────────────────────────────────────────────────────────────
 * PROBLEM STATEMENT:
 *
 *   Model a simple library system with the following classes:
 *
 *   1. class LibraryItem
 *      - Private members: string title, string itemId
 *      - Protected member: bool isAvailable (default: true)
 *      - Parameterized constructor (title, itemId)
 *      - Public methods:
 *          * getTitle()     → returns title
 *          * getId()        → returns itemId
 *          * checkStatus()  → prints "Available" or "Checked Out"
 *
 *   2. class Book : public LibraryItem
 *      - Private members: string author, int pages
 *      - Constructor that takes (title, itemId, author, pages)
 *        and forwards title + itemId to LibraryItem's constructor
 *      - Public methods:
 *          * checkout()  → sets isAvailable = false, prints "Book checked out"
 *          * returnBook()→ sets isAvailable = true,  prints "Book returned"
 *          * getInfo()   → prints title, author, pages, and availability
 *
 *   3. class Magazine : public LibraryItem
 *      - Private members: int issueNumber, string publisher
 *      - Constructor that takes (title, itemId, issueNumber, publisher)
 *      - Public methods:
 *          * getInfo() → prints title, issue number, publisher, and availability
 *
 * ─────────────────────────────────────────────────────────────
 * REQUIREMENTS:
 *   - Use member initializer lists in ALL constructors
 *   - 'isAvailable' must only be accessed through 'protected'
 *     (not re-declared in derived classes)
 *   - Do NOT use any virtual functions in this exercise
 *
 * ─────────────────────────────────────────────────────────────
 * EXPECTED main() OUTPUT (write this main to test your code):
 *
 *   Book b("The Pragmatic Programmer", "B001", "David Thomas", 352);
 *   b.getInfo();       // Available
 *   b.checkout();
 *   b.checkStatus();   // Checked Out
 *   b.returnBook();
 *   b.checkStatus();   // Available
 *
 *   Magazine m("Forbes", "M001", 212, "Forbes Media");
 *   m.getInfo();
 * ============================================================
 */

#include <iostream>
#include <string>
using namespace std;

// ─── Write your code below ────────────────────────────────────

class LibraryItem{
    private:
        string title;
        string itemId;
    protected:
        bool isAvailable;
    public:
        LibraryItem(string title,string itemId): title(title),itemId(itemId), isAvailable(true){}

        string getTitle(){
            return title;
        }

        string getId(){
            return itemId;
        }

        void checkStatus(){
            if(isAvailable){
                cout<<"Available"<<endl;
            } else {
                cout<<"Checked Out"<<endl;
            }
        }
};

class Book: public LibraryItem{
    private:
        string author;
        int pages;
    public:
        Book(string title,string itemId,string author,int pages): LibraryItem(title,itemId),author(author),pages(pages){}

        void checkout(){
            if(!isAvailable){
                return;
            }
            isAvailable=false;
            cout<<"Book checked out"<<endl;
        }

        void returnBook(){
            if(isAvailable){
                return;
            }
            isAvailable=true;
            cout<<"Book returned"<<endl;
        }

        void getInfo(){
            cout<<getTitle()<<" "<<author<<" "<<pages<<" ";
            checkStatus();
        }

};

class Magazine: public LibraryItem{
    private:
        int issueNumber;
        string publisher;
    public:
        Magazine(string title,string itemId,int issueNumber,string publisher): LibraryItem(title,itemId),issueNumber(issueNumber),publisher(publisher){}
        
        void getInfo(){
            cout<<getTitle()<<" "<<publisher<<" "<<issueNumber<<" ";
            checkStatus();
        }
};


int main(){
    Book b("The Pragmatic Programmer", "B001", "David Thomas", 352);
    b.getInfo();       // Available
    b.checkout();
    b.checkStatus();   // Checked Out
    b.returnBook();
    b.checkStatus();   // Available
 
    Magazine m("Forbes", "M001", 212, "Forbes Media");
    m.getInfo();
}