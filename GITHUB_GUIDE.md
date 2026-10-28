# Upload to your chosen GitHub repository

Project repository: https://github.com/25ce138/JAVA-MINI-PROJECT

The commands below document the initial upload. For later changes, use your existing clone and run git add, git commit and git push.

```bash
git init
git branch -M main
git add .
git commit -m "Add MiniBank Java practicals 1 to 14 with report and output"
git remote add origin https://github.com/25ce138/JAVA-MINI-PROJECT.git
git push -u origin main
```

If Git asks for your identity, set your own git user.name and user.email. Authenticate through GitHub's normal sign-in flow. Never put a password or token in the project files.

If the repository already has files, clone it first, copy this project into a new subfolder, inspect git status, then add and commit that subfolder. Do not force-push or replace existing coursework.

For later practical changes:
```bash
git add Practical-01
git commit -m "Update practical 1 menu"
git push
```

Use truthful commit messages. The prepared project includes all practicals now; do not backdate practical work.
