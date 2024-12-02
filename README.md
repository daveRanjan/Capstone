### CAPSTONE PROJECT: BACKEND SPECIALIZATION 

### Structure of the project:
```
├── Capstone (Parent Module)
├──── Product Service
├──── Order Service
├──── User Service
├──── Catalog Service
├──── Cart Service
├──── Payment Service
```
### Project Management
Product Management is done via Backlog tool. It is free for small projects and suitable for the my needs to manage this projects. 

All the commit will have the task-id usually looks like this 

``` **[CAPSTONE-XXX]** | Short Commit Message | Optional Long Commit Details ``` 

### Branching and Management

- ***master*** - This is the main branch where the source code of HEAD always reflects a production-ready state.
- **develop** - This is the main branch where the source code of HEAD always reflects a state with the latest delivered development changes for the next release. Some would call this the “integration branch”. This is where any automatic nightly builds are built from.
- **feature/<feature-name>** - This is the branch where the feature is developed. When the feature is complete, it is merged back into develop.
- **task/CAPSTONE-XXX** - This is the branch where the task is developed. When the task is complete, it is merged back into feature/<feature-name.
- **bug/CAPSTONE-XXX** - This is the branch where the bug is fixed. When the bug is fixed, it is merged back into develop.

Restricted Banches:-
- **master** - No direct commits are allowed to this branch. All the code will be merged only via Pull Requests.
- **develop** - No direct commits are allowed to this branch.
