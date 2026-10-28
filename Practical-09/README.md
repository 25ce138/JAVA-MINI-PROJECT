# Practical 9 — Threads and synchronization

Start ten worker threads and join them. Use synchronized methods to prevent lost deposits.

Run `run.bat` on Windows or `bash run.sh` on Linux/macOS. Compile only this folder. Requires JDK 17+.

Expected result: Ten threads each add 1000 units. The final synchronized balance is exactly 10000.
