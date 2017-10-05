package com.company;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) {

    }

    private static void atomicEx() {
        // Атомарные тип для всех типов есть ссылочных, это синхронизированный тип данных.
        AtomicInteger ai = new AtomicInteger(10);
        ai.addAndGet(40); // Между прибавлением и гетом никто не может вклинится, там внутри своя синхронизация
    }

    private static void lockReadWriteEx() {
        // В момент записи читать нельзя и наоборот и так можно очередь нагнать, одновременно могут только кучи читателей, но кучи писателей не может писать
        ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();

        rrwl.readLock().lock();
        rrwl.readLock().unlock();
        rrwl.writeLock().lock();
        rrwl.writeLock().unlock();
    }

    private static void lockEx() {
        // Можно блокировать по замку выполнение, разница с другими в том, что можно разорвать замок в другом месте (unlock сделать), а можно попытаться захватить замок (tryLock)
        ReentrantLock lock = new ReentrantLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // lock.lock();
                    if (lock.tryLock()) {
                        System.out.println(1);
                        Thread.sleep(2000);
                        System.out.println(2);
                        lock.unlock();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    lock.lock();
                    System.out.println(3);
                    Thread.sleep(2000);
                    System.out.println(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (lock.tryLock(3, TimeUnit.SECONDS)) {
                        System.out.println(5);
                        Thread.sleep(2000);
                        System.out.println(6);
                        lock.unlock();
                    } else {
                        System.out.println(7);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void barrierEx() {
        // Одновременно запускает потоки, которые готовятся разное время, каждый сообщает о своей готовности и ждёт пока другие скажут. Когда все подготовятся, то все одновременно рвутся вперёд.
        CyclicBarrier cb = new CyclicBarrier(3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("READY");
                    // Тут поток стоит пока другие потоки не сделаю это же самое
                    try {
                        cb.await(); // Его можно несколько раз запускать. Типа 3 раза жмякнули, а потом ещё раза 3 можно.
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("GO");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    System.out.println(cb.getParties());
                    System.out.println(cb.getNumberWaiting());
                    System.out.println("READY");
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("GO");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("READY");
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("GO");
            }
        }).start();
    }

    private static void countDownEx() {
        ExecutorService serv = Executors.newFixedThreadPool(10);
        // Синхронизация без join, сами потоки сообщают что закончили
        CountDownLatch cdl = new CountDownLatch(3);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cdl.countDown();
            }
        });
        t.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cdl.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(3);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cdl.countDown();
            }
        }).start();
        System.out.println(4);
        cdl.countDown();
        try {
            // Тут ждёт текущий main-поток
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("END");
    }

    private static void semaphoreEx() {
        ExecutorService serv = Executors.newFixedThreadPool(10);
        Semaphore smp = new Semaphore(4);
        for (int i = 0; i < 10; i++) {
            serv.execute(() -> {
                try {
                    smp.acquire();
                    System.out.println(1);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    smp.release();
                }
            });
        }

        serv.shutdown();
    }

    private static void dataPutTakeEx() {
        // Для передачи данных между потоками, блокирующая очередь
        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(6);
        // Есть обычные операции, а есть блокирующие, они ниже
        // abq.put();
        // abq.take();

        // Для синхронизации помещение в мэп данных
        // ConcurrentHashMap
        // Это не тоже самое, а гораздо хуже. Например, потому что блокировка и на чтение ставится
        // HashMap hm;
        // Map map = Collections.synchronizedMap(hm);

        // При чтении блокируется и если что-то добавляем, то создаётся своя копия для записывающего треда
        // CopyOnWriteArrayList<String> cow = new CopyOnWriteArrayList<>();
    }

    private static void schedulerEx() {
        // По расписанию запуск задач
        ScheduledExecutorService serv = Executors.newScheduledThreadPool(5);

        serv.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(2);
            }
        }, 0, 3, TimeUnit.SECONDS);
        // Можно запустить любую команду через время
        // serv.schedule();
        // serv.scheduleWithFixedDelay()
        // serv.scheduleAtFixedRate()
    }

    private static void futureTaskEx() throws InterruptedException {
        FutureTask<String> ft = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(5000);
                return "Java";
            }
        });

        ExecutorService serv = Executors.newFixedThreadPool(3);
        serv.submit(ft);
    }

    private static void poolEx() throws InterruptedException {
        // Больше трёх поток он не создаст, сколько бы вход тасков не подали
        ExecutorService serv = Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("-");
                Thread t = new Thread(r);
                t.setPriority(10);
                return t;
            }
        });

        // Если задачи кончились, то он заказчивает свою работу
        // serv.shutdown();
        // Вызывает interapt у потоков, но не вырубает их насильно. Если задача не обрабатывает interapt, то ничего не будет
        // serv.shutdownNow();
        // Останавливается в выполнении текущий поток main
        // serv.awaitTermination(10, TimeUnit.MINUTES);

        // Future future = serv.submit(new Runnable() {
        //     @Override
        //     public void run() {
        //         try {
        //             Thread.sleep(5000);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // });

        Future<String> future = serv.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "Java";
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("END");
    }
}
