# Practical 10 — Thread pool and deadlock prevention

Use a fixed thread pool, a wait and notify buffer, and ordered locks for transfers.

Run `run.bat` on Windows or `bash run.sh` on Linux/macOS. Compile only this folder. Requires JDK 17+.

Expected result: The worker pool completes opposing transfers without deadlock. The consumer receives a buffer entry.
